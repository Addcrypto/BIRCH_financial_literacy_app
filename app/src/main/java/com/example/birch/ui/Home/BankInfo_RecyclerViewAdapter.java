package com.example.birch.ui.Home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birch.R;
import com.example.birch.balance.Accounts;
import com.example.birch.models.BankInfoModel;
import com.plaid.link.result.LinkAccount;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class BankInfo_RecyclerViewAdapter extends RecyclerView.Adapter<BankInfo_RecyclerViewAdapter.BankInfoViewHolder> {
    Context context;
    // ArrayList<BankInfoModel> bankInfoModels;
    Accounts[] bankInfoModels = {};

    // get values for the context and model
    // public BankInfo_RecyclerViewAdapter (Context context, Accounts[] bankInfoModels) {
    public BankInfo_RecyclerViewAdapter (Context context) {
        this.context = context;
    }

    public void setAccounts (Accounts[] accounts) {
    // public void setAccounts (ArrayList<BankInfoModel> accounts) {
        this.bankInfoModels = accounts;
    }

    public Accounts[] getAccounts () {
    // public ArrayList<BankInfoModel> getAccounts () {
        return this.bankInfoModels;
    }

    @NonNull
    @Override
    public BankInfo_RecyclerViewAdapter.BankInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_home_your_financials, parent, false);

        return new BankInfo_RecyclerViewAdapter.BankInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BankInfo_RecyclerViewAdapter.BankInfoViewHolder holder, int position) {
        // assigning values to the views created in the "home_your_financials_row" layout file
        // based on the position of the recycler view

        Double currentBalance = Double.parseDouble(bankInfoModels[position].getBalances().getCurrent());
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String fmt = formatter.format(currentBalance);

        holder.tv_bankName.setText(bankInfoModels[position].getName());
        holder.tv_accountTotal.setText(fmt);

        // holder.tv_bankName.setText(bankInfoModels.get(position).getBankName());
        // holder.tv_accountTotal.setText(bankInfoModels.get(position).getAccountTotal());
    }

    @Override
    public int getItemCount() {
        // return bankInfoModels.size();
        return bankInfoModels.length;
    }


    public static class BankInfoViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tv_bankName, tv_accountTotal;

        // getting the views from "home_your_financials_row" file and assigning to vars
        // Kinda like the onCreate method
        public BankInfoViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_bankName = itemView.findViewById(R.id.tv_home_yourFinancialsBankAccount);
            tv_accountTotal = itemView.findViewById(R.id.tv_home_yourFinancialsBankTotal);
        }
    }
}
