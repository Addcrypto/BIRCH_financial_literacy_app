package com.example.birch.ui.YourFinancials;

import android.content.Context;
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

import com.example.birch.R;
import com.example.birch.RegisterActivity;
import com.example.birch.models.BankInfoModel;
import com.example.birch.ui.Home.BankInfo_RecyclerViewAdapter;

import com.plaid.link.OpenPlaidLink;
import com.plaid.link.configuration.LinkTokenConfiguration;
import com.plaid.link.result.LinkAccount;
import com.plaid.link.result.LinkAccountBalance;
import com.plaid.link.result.LinkError;
import com.plaid.link.result.LinkErrorCode;
import com.plaid.link.result.LinkExit;
import com.plaid.link.result.LinkSuccess;
import com.plaid.link.result.LinkSuccessMetadata;

import java.util.ArrayList;
import java.util.Arrays;

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

    //Retrieval of Link Token
    LinkTokenConfiguration linkTokenConfiguration = new LinkTokenConfiguration.Builder()
            .token("LINK_TOKEN_FROM_SERVER")
            .build();

    //Retrieval of data
    private ActivityResultLauncher<LinkTokenConfiguration> linkAccountToPlaid = registerForActivityResult(
            new OpenPlaidLink(),
            result -> {
                if (result instanceof LinkSuccess) {
                    LinkSuccess success = (LinkSuccess) result;

                    String publicToken = success.getPublicToken();
                    LinkSuccessMetadata metadata = success.getMetadata();
                    for(LinkAccount account : success.component2().getAccounts()){
                        String accountId = account.getId();
                        String accountName = account.getName();
                        String accountMask = account.getMask();
                        LinkAccountBalance accountBalance = account.getBalance();
                    }
                    String bankName = metadata.getInstitution().getName();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_your_financials, container, false);
        Context ctx = getActivity().getApplicationContext();

        btn_linkAccount = view.findViewById(R.id.btn_linkBank);
        btn_linkDebt = view.findViewById(R.id.btn_linkDebt);

        btn_linkAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ctx, "link account clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btn_linkDebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ctx, "link debt clicked", Toast.LENGTH_SHORT).show();
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