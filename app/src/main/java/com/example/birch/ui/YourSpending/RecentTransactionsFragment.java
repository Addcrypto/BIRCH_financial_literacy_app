package com.example.birch.ui.YourSpending;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.birch.R;
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
        String[] totals = {"-$30.00", "-$20", "-$100"};


        for(int i = 0; i < titles.length; i++) {
            transactionModels.add(new TransactionModel(totals[i], titles[i], "11/11/22"));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recent_transactions, container, false);
        Context ctx = getActivity().getApplicationContext();

        RecyclerView rv_transactions = view.findViewById(R.id.rv_recentTransactions);
        rv_transactions.setHasFixedSize(true);

        RecentTransactionsAdapter adapter = new RecentTransactionsAdapter(ctx, transactionModels);
        rv_transactions.setAdapter(adapter);
        rv_transactions.setLayoutManager(new LinearLayoutManager(ctx));

        return view;
    }
}