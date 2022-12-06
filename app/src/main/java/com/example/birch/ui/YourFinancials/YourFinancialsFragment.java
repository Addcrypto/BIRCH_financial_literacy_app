package com.example.birch.ui.YourFinancials;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.birch.Liabilities.LiabilitiesModel;
import com.example.birch.R;
import com.example.birch.SP_LocalStorage;
import com.example.birch.balance.Accounts;
import com.example.birch.balance.BalanceModel;
import com.example.birch.dto.AccessTokModel;
import com.example.birch.dto.TokenModel;
import com.example.birch.models.BankInfoModel;
import com.example.birch.network.LinkApi;
import com.example.birch.network.LinkTokenRequester;
import com.example.birch.ui.Home.BankInfo_RecyclerViewAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YourFinancialsFragment extends Fragment {
    ArrayList<BankInfoModel> bankInfoModels = new ArrayList<>();
    ArrayList<BankInfoModel> debtInfoModels = new ArrayList<>();
    // List<LinkAccount> plaidAccounts;
    Accounts plaidAccounts[] = {};
    Button btn_linkAccount;
    Button btn_linkDebt;
    Button btn_initLink;
    String publicToken;
    String accessToken;

    String currentUserEmail;
    Boolean isLinked;

    String totalMoney;
    String totalDebt;

    SP_LocalStorage storage;
    SharedPreferences.Editor editor;

    private FirebaseAuth mFirebaseAuth;         // Firebase Authentication
    private DatabaseReference mDatabaseRef;     // Real time Firebase Connection

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
                    // plaidAccounts = success.component2().getAccounts();
                    // Log.i("---plaid accounts", plaidAccounts.get(0).getName());
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

                            // Map access token to user
                            mDatabaseRef.child("users").child(currentUserEmail).child("accessToken").setValue(accessToken);
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

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://birch-finanancial-literacy-app-default-rtdb.firebaseio.com/");
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

//    private void calculateTotals() {
//        plaidAccounts
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_your_financials, container, false);
        Context ctx = getActivity().getApplicationContext();
        storage = new SP_LocalStorage(ctx);
        linkApi = LinkTokenRequester.getInstance().getLinkAPI();

        // Views
        RecyclerView rv_yourFinancials = view.findViewById(R.id.rv_home_YourFinancials);
        ConstraintLayout cl_yourFinancials = view.findViewById(R.id.yourFinancials_layout);

        RecyclerView rv_yourFinancials_debt = view.findViewById(R.id.rv_yourFinancials_Debt);
        ConstraintLayout cl_yourDebt = view.findViewById(R.id.yourFinancials_debt_layout);

        TextView isLinkedText = view.findViewById(R.id.tv_notLinkedMessage);

        btn_linkAccount = view.findViewById(R.id.btn_linkBank);
        btn_linkDebt = view.findViewById(R.id.btn_linkDebt);
        btn_initLink = view.findViewById(R.id.bt_initLink);

        // Local Storage
        currentUserEmail = storage.getCurrentUserEmail();
        isLinked = storage.getIsLinked();


        if(isLinked) {
            btn_initLink.setVisibility(View.INVISIBLE);
            isLinkedText.setVisibility(View.INVISIBLE);
            cl_yourFinancials.setVisibility(View.VISIBLE);

            // get access token from user
            accessToken = storage.getAccessToken();

            BankInfo_RecyclerViewAdapter financials_adapter = new BankInfo_RecyclerViewAdapter(ctx);
            rv_yourFinancials.setAdapter(financials_adapter);
            rv_yourFinancials.setLayoutManager(new LinearLayoutManager(ctx));

            BankInfo_RecyclerViewAdapter debt_adapter = new BankInfo_RecyclerViewAdapter(ctx);
            rv_yourFinancials_debt.setAdapter(debt_adapter);
            rv_yourFinancials_debt.setLayoutManager(new LinearLayoutManager(ctx));

            // Get balance from api
            linkApi.getBalance(accessToken).enqueue(new Callback<BalanceModel>() {
                @Override
                public void onResponse(Call<BalanceModel> call, Response<BalanceModel> response) {
                    // System.out.println(response.body().getAccounts());
                    plaidAccounts = response.body().getAccounts();

                    // Update recycler view
                    financials_adapter.setAccounts(plaidAccounts);
                    rv_yourFinancials.setAdapter(financials_adapter);

                    /*
                    debt_adapter.setAccounts(plaidAccounts);
                    rv_yourFinancials_debt.setAdapter(financials_adapter);
                     */
                }

                @Override
                public void onFailure(Call<BalanceModel> call, Throwable error) {
                    Log.i("GET BALANCE ERROR", error.toString());
                    onLinkTokenError(error);
                }
            });
        } else {
            isLinkedText.setVisibility(View.VISIBLE);
        }


        // Event Listeners
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



        // Inflate the layout for this fragment
        return view;
    }
}