package com.example.birch.ui.YourSpendings;

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
public class UpcomingTransactionsFragment extends Fragment {
    ArrayList<TransactionModel> transactionModels = new ArrayList<>();

    public UpcomingTransactionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupTransactionModels();
    }

    // for testing
    private void setupTransactionModels() {
        String[] titles = {"Spotify Premium", "Credit Card"};
        String[] totals = {"$10", "$250"};

        for(int i = 0; i < titles.length; i++) {
            transactionModels.add(new TransactionModel(totals[i], titles[i], "11/11/22"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming_transactions, container, false);
        Context ctx = getActivity().getApplicationContext();

        RecyclerView rv_transactions = view.findViewById(R.id.rv_upcomingTransactions);
        rv_transactions.setHasFixedSize(true);

        UpcomingTransactionsAdapter adapter = new UpcomingTransactionsAdapter(ctx, transactionModels);
        rv_transactions.setAdapter(adapter);
        rv_transactions.setLayoutManager(new LinearLayoutManager(ctx));

        return view;
    }
}