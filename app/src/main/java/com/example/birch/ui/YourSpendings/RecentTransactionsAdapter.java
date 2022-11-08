package com.example.birch.ui.YourSpendings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birch.R;
import com.example.birch.models.TransactionModel;

import java.util.ArrayList;

// public class BankInfo_RecyclerViewAdapter extends RecyclerView.Adapter<com.example.birch.ui.Home.BankInfo_RecyclerViewAdapter.BankInfoViewHolder> {
public class RecentTransactionsAdapter extends RecyclerView.Adapter<RecentTransactionsAdapter.RecentTransactionsViewHolder> {
    Context context;
    ArrayList<TransactionModel> transactionModels;

    // get values for the context and model
    public RecentTransactionsAdapter(Context context, ArrayList<TransactionModel> transactionModels) {
        this.context = context;
        this.transactionModels = transactionModels;
    }

    @NonNull
    @Override
    public RecentTransactionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_transaction, parent, false);

        return new RecentTransactionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentTransactionsViewHolder holder, int position) {
        // assigning values to the views created in the "home_your_financials_row" layout file
        // based on the position of the recycler view

        holder.tv_title.setText(transactionModels.get(position).getTitle());
        holder.tv_total.setText(transactionModels.get(position).getTotal());
        // holder.tv_date.setText(transactionModels.get(position).getDate());

    }

    @Override
    public int getItemCount() { return transactionModels.size(); }

    public static class RecentTransactionsViewHolder extends RecyclerView.ViewHolder {

        TextView tv_total, tv_title;

        // getting the views from "list_item_transaction" file and assigning to vars
        // Kinda like the onCreate method
        public RecentTransactionsViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_transaction_title);
            tv_total = itemView.findViewById(R.id.tv_transaction_total);
        }
    }
}
