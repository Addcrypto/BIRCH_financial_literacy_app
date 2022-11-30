package com.example.birch.ui.YourSpending;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.birch.R;
import com.example.birch.SP_LocalStorage;
import com.example.birch.models.TransactionModel;
import com.example.birch.models.UpcomingTransactionModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingTransactionsFragment extends Fragment {
    // ArrayList<TransactionModel> transactionModels = new ArrayList<>();
    FloatingActionButton btn_addUpcomingTransaction;
    View view;
    DAOUpcomingTransaction dao;
    UpcomingTransactionsAdapter adapter;
    SP_LocalStorage storage;
    TextView tv_noBills;
    RecyclerView rv_transactions;

    String ownerEmail;

    public UpcomingTransactionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void loadData() {
        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<UpcomingTransactionModel> transactionModels = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    // TODO: figure out how to filter with firebase data instead of this if statement
                    UpcomingTransactionModel tr = data.getValue(UpcomingTransactionModel.class);
                    // check if transaction is owned by logged in user
                    if (tr.getOwnerEmail().equals(ownerEmail)) {
                        // Log.i("item key", data.getKey());
                        tr.setId(data.getKey());
                        transactionModels.add(tr);
                    }
                }

                if(transactionModels.isEmpty()) {
                    tv_noBills.setVisibility(View.VISIBLE);
                    rv_transactions.setVisibility(View.INVISIBLE);
                } else {
                    tv_noBills.setVisibility(View.INVISIBLE);
                    rv_transactions.setVisibility(View.VISIBLE);
                    adapter.setItems(transactionModels);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    static String encodeEmail(String user) {
        return user.replace(".", ",");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_upcoming_transactions, container, false);
        Context ctx = getActivity().getApplicationContext();
        dao = new DAOUpcomingTransaction();
        btn_addUpcomingTransaction = (FloatingActionButton) view.findViewById(R.id.btn_addUpcomingTransaction);
        tv_noBills = view.findViewById(R.id.tv_noBills);

        storage = new SP_LocalStorage(ctx);
        SharedPreferences.Editor editor = storage.getEditor();

        ownerEmail = encodeEmail(storage.getCurrentUserEmail());
        Log.i("Storage Email", ownerEmail);

        rv_transactions = view.findViewById(R.id.rv_upcomingTransactions);
        rv_transactions.setHasFixedSize(true);
        rv_transactions.setLayoutManager(new LinearLayoutManager(ctx));

        adapter = new UpcomingTransactionsAdapter(ctx);
        rv_transactions.setAdapter(adapter);

        // Call load data after adapter is created to call .setItems() in loadData()
        loadData();

        adapter.setOnItemClickListener(new UpcomingTransactionsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(UpcomingTransactionModel tr) {
                // Log.i("on item click listener", "clicked...");
                Navigation.findNavController(view).navigate(R.id.createUpcomingTransactionFragment);

                // Set up up flags in localstorage
                editor.putBoolean("isEditingBill", true);
                editor.putString("currentBillId", tr.getId());
                editor.apply();
                Log.i("UpcomingTransFragment", "Bill item selected. isEditingBill: " + Boolean.toString(storage.getIsEditingBill()));
            }
        });


        btn_addUpcomingTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.createUpcomingTransactionFragment);
            }
        });

        return view;
    }
}