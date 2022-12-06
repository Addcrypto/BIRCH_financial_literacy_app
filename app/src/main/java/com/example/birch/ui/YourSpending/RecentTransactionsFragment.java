package com.example.birch.ui.YourSpending;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.birch.R;
import com.example.birch.SP_LocalStorage;
import com.example.birch.models.TransactionModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentTransactionsFragment extends Fragment {
    ArrayList<TransactionModel> transactionModels = new ArrayList<>();

    public RecentTransactionsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupTransactionModels();
    }

    // for testing
    private void setupTransactionModels() {
        String[] titles = {"Gas Station", "Restaurant", "Grocery Store"};
        String[] totals = {"30.00", "20", "100"};


        for(int i = 0; i < titles.length; i++) {
            transactionModels.add(new TransactionModel(totals[i], titles[i], "Tuesday, December 6, 2022"));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recent_transactions, container, false);
        Context ctx = getActivity().getApplicationContext();

        SP_LocalStorage storage = new SP_LocalStorage(ctx);

        TextView notLinkedMessage = view.findViewById(R.id.tv_trans_notLinkedMessage);

        Boolean isLinked = storage.getIsLinked();

        RecyclerView rv_transactions = view.findViewById(R.id.rv_recentTransactions);
        rv_transactions.setHasFixedSize(true);

        if(isLinked) {
            rv_transactions.setVisibility(View.VISIBLE);
            notLinkedMessage.setVisibility(View.INVISIBLE);

            RecentTransactionsAdapter adapter = new RecentTransactionsAdapter(ctx, transactionModels);
            rv_transactions.setAdapter(adapter);
            rv_transactions.setLayoutManager(new LinearLayoutManager(ctx));
        } else {
            notLinkedMessage.setVisibility(View.VISIBLE);
            rv_transactions.setVisibility(View.INVISIBLE);
        }


        return view;
    }
}