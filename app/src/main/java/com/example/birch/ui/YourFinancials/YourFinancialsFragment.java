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

import com.example.birch.R;
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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public YourFinancialsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment your_financials.
     */
    // TODO: Rename and change types and number of parameters
    public static YourFinancialsFragment newInstance(String param1, String param2) {
        YourFinancialsFragment fragment = new YourFinancialsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        TO DO:
        OnClickLister for each link button
         */

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

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