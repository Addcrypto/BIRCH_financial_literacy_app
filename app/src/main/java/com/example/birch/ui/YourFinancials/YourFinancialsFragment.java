package com.example.birch.ui.YourFinancials;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.birch.Liabilities.LiabilitiesModel;
import com.example.birch.R;
import com.example.birch.SP_LocalStorage;
import com.example.birch.balance.BalanceModel;
import com.example.birch.dto.AccessTokModel;
import com.example.birch.dto.TokenModel;
import com.example.birch.models.BankInfoModel;
import com.example.birch.network.LinkApi;
import com.example.birch.network.LinkTokenRequester;
import com.example.birch.ui.Home.BankInfo_RecyclerViewAdapter;

import com.plaid.link.OpenPlaidLink;
import com.plaid.link.configuration.LinkTokenConfiguration;
import com.plaid.link.result.LinkAccount;
import com.plaid.link.result.LinkAccountBalance;
import com.plaid.link.result.LinkAccountSubtype;
import com.plaid.link.result.LinkError;
import com.plaid.link.result.LinkErrorCode;
import com.plaid.link.result.LinkExit;
import com.plaid.link.result.LinkSuccess;
import com.plaid.link.result.LinkSuccessMetadata;


import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link YourFinancialsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YourFinancialsFragment extends Fragment {
    ArrayList<BankInfoModel> bankInfoModels = new ArrayList<>();
    ArrayList<BankInfoModel> debtInfoModels = new ArrayList<>();
    Button btn_linkAccount;
    Button btn_linkDebt;
    Button btn_initLink;
    String publicToken;
    String accessToken;

    SP_LocalStorage storage;
    SharedPreferences.Editor editor;

    private LinkApi linkApi;


    //Retrieval of data
    private ActivityResultLauncher<LinkTokenConfiguration> linkAccountToPlaid = registerForActivityResult(
            new OpenPlaidLink(),
            result -> {
                if (result instanceof LinkSuccess) {
                    //Context ctx = getActivity().getApplicationContext();
                    linkApi = LinkTokenRequester.getInstance().getLinkAPI();
                    LinkSuccess success = (LinkSuccess) result;

                    publicToken = success.getPublicToken();
                    System.out.println(publicToken);

                    LinkSuccessMetadata metadata = success.getMetadata();
                    for(LinkAccount account : success.component2().getAccounts()){
                        String accountName = account.getName();
                        String subtype = account.getSubtype().toString();
                        subtype = subtype.substring(subtype.indexOf("$"), subtype.indexOf("@"));
                        System.out.println(accountName+"\n"+subtype);
                    }
                    String bankName = metadata.getInstitution().getName();
                    System.out.println(bankName);
                    System.out.println(metadata.getMetadataJson());

                    linkApi.getAccToken(publicToken).enqueue(new Callback<AccessTokModel>() {
                        @Override
                        public void onResponse(Call<AccessTokModel> call, Response<AccessTokModel> response) {
                            accessToken = response.body().getAccess_token();
                            System.out.println(accessToken);
                        }

                        @Override
                        public void onFailure(Call<AccessTokModel> call, Throwable error) {
                            onLinkTokenError(error);
                        }
                    });
                }
                else {
                    LinkExit exit = (LinkExit) result;
                    LinkError error = exit.getError();
                    LinkErrorCode errorCode = error.getErrorCode();
                    String errorMessage = error.getErrorMessage();
                    String displayMessage = error.getDisplayMessage();
                }
            }
    );

    public YourFinancialsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // always set up models before passing to recycler view
        setUpBankInfoModels();
    }

    public void setUpBankInfoModels() {
        // TODO: get this info from API
        String[] bankNames = {"Chase (Checking)", "Chase (Savings)", "Discover (Credit Card)"};
        String[] accountType = {"Checking", "Savings", "Credit"};
        String[] accountTotals = {"$3,343.88", "$1,182.89", "$832.32"};

        String[] debtNames = {"Discover (Credit Card)", "Car Loan"};
        String[] debtType = {"Credit Card", "Loan"};
        String[] debtTotals = {"$168", "$5,000"};

        for(int i = 0; i < bankNames.length; i++) {
            bankInfoModels.add(new BankInfoModel(bankNames[i], accountTotals[i], accountType[i]));
        }

        for(int i = 0; i < debtNames.length; i++) {
            debtInfoModels.add(new BankInfoModel(debtNames[i], debtTotals[i], debtType[i]));
        }
    }

    /**
     * For all Link configuration options, have a look at the
     * <a href="https://plaid.com/docs/link/android/#parameter-reference">parameter reference</>
     */
    private void openLink() {
        linkApi = LinkTokenRequester.getInstance().getLinkAPI();
        //System.out.println(linkApi.getLinkToken());
        linkApi.getLinkToken().enqueue(new Callback<TokenModel>() {
            @Override
            public void onResponse(Call<TokenModel> call, Response<TokenModel> response) {
                String token = response.body().getLink_token();
                onLinkTokenSuccess(token);
            }

            @Override
            public void onFailure(Call<TokenModel> call, Throwable error) {
                onLinkTokenError(error);
            }
        });
    }

    private void onLinkTokenSuccess(String token) {
        LinkTokenConfiguration configuration = new LinkTokenConfiguration.Builder()
                .token(token)
                .build();
        linkAccountToPlaid.launch(configuration);
    }

    private void onLinkTokenError(Throwable error) {
        Context ctx = getActivity().getApplicationContext();
        if (error instanceof java.net.ConnectException) {
            Toast.makeText(ctx, "There was an error while linking to your bank. Try again later.", Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(ctx, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_your_financials, container, false);
        Context ctx = getActivity().getApplicationContext();

        btn_linkAccount = view.findViewById(R.id.btn_linkBank);
        btn_linkDebt = view.findViewById(R.id.btn_linkDebt);
        btn_initLink = view.findViewById(R.id.bt_initLink);

        btn_linkAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linkApi.getBalance(accessToken).enqueue(new Callback<BalanceModel>() {
                    @Override
                    public void onResponse(Call<BalanceModel> call, Response<BalanceModel> response) {
                        System.out.println(response);
                    }

                    @Override
                    public void onFailure(Call<BalanceModel> call, Throwable error) {
                        onLinkTokenError(error);
                    }
                });
            }
        });

        btn_linkDebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linkApi.getLiabilities().enqueue(new Callback<LiabilitiesModel>() {
                    @Override
                    public void onResponse(Call<LiabilitiesModel> call, Response<LiabilitiesModel> response) {
                        System.out.println(response);
                    }

                    @Override
                    public void onFailure(Call<LiabilitiesModel> call, Throwable error) {
                        onLinkTokenError(error);
                    }
                });
            }
        });

        btn_initLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLink();
            }
        });

        RecyclerView rv_yourFinancials = view.findViewById(R.id.rv_home_YourFinancials);
        BankInfo_RecyclerViewAdapter adapter = new BankInfo_RecyclerViewAdapter(ctx, bankInfoModels);
        rv_yourFinancials.setAdapter(adapter);
        rv_yourFinancials.setLayoutManager(new LinearLayoutManager(ctx));

        RecyclerView rv_yourFinancials_debt = view.findViewById(R.id.rv_yourFinancials_Debt);
        BankInfo_RecyclerViewAdapter adapter_2 = new BankInfo_RecyclerViewAdapter(ctx, debtInfoModels);
        rv_yourFinancials_debt.setAdapter(adapter_2);
        rv_yourFinancials_debt.setLayoutManager(new LinearLayoutManager(ctx));

        // Inflate the layout for this fragment
        return view;
    }
}