package com.example.birch.ui.YourSpending;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birch.R;
import com.example.birch.models.TransactionModel;
import com.example.birch.models.UpcomingTransactionModel;

import java.util.ArrayList;

public class UpcomingTransactionsAdapter extends RecyclerView.Adapter<UpcomingTransactionsAdapter.UpcomingTransactionsViewHolder> {
    Context context;
    // ArrayList<TransactionModel> transactionModels = new ArrayList<>();
    ArrayList<UpcomingTransactionModel> transactionModels = new ArrayList<>();

    // get values for the context and model
    // public UpcomingTransactionsAdapter(Context context, ArrayList<TransactionModel> transactionModels) {
    public UpcomingTransactionsAdapter(Context context) {
        this.context = context;
        // this.transactionModels = transactionModels;
    }

    public void setItems(ArrayList<UpcomingTransactionModel> tr) {
        transactionModels.addAll(tr);
    }

    @NonNull
    @Override
    public UpcomingTransactionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_upcoming_transaction, parent, false);

        return new UpcomingTransactionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingTransactionsViewHolder holder, int position) {
        // assigning values to the views created in the "home_your_financials_row" layout file
        // based on the position of the recycler view

        /*
        holder.tv_title.setText(transactionModels.get(position).getName());
        holder.tv_total.setText(String.format("%.02f", transactionModels.get(position).getAmount()));
        holder.tv_date.setText(transactionModels.get(position).getDueDate());
        */


        holder.tv_name.setText(transactionModels.get(position).getName());
        holder.tv_amount.setText(String.format("%.02f", transactionModels.get(position).getAmount()));
        holder.tv_date.setText(transactionModels.get(position).getDueDate());
        holder.tv_repeats.setText(transactionModels.get(position).getRepeats());
    }

    @Override
    public int getItemCount() { return transactionModels.size(); }

    public static class UpcomingTransactionsViewHolder extends RecyclerView.ViewHolder {

        // TextView tv_total, tv_title, tv_date;
        TextView tv_amount, tv_name, tv_date, tv_repeats;

        // getting the views from "list_item_transaction" file and assigning to vars
        // Kinda like the onCreate method
        public UpcomingTransactionsViewHolder(@NonNull View itemView) {
            super(itemView);

//            tv_title = itemView.findViewById(R.id.tv_transaction_title);
//            tv_total = itemView.findViewById(R.id.tv_transaction_total);
//            tv_date = itemView.findViewById(R.id.tv_transaction_date);

            tv_name = itemView.findViewById(R.id.tv_billName);
            tv_amount = itemView.findViewById(R.id.tv_billAmount);
            tv_date = itemView.findViewById(R.id.tv_billDate);
            tv_repeats = itemView.findViewById(R.id.tv_billRepeats);
        }
    }
}
