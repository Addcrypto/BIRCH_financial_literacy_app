package com.example.birch.ui.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birch.R;
import com.example.birch.models.BankInfoModel;

import java.util.ArrayList;

public class BankInfo_RecyclerViewAdapter extends RecyclerView.Adapter<BankInfo_RecyclerViewAdapter.BankInfoViewHolder> {
    Context context;
    ArrayList<BankInfoModel> bankInfoModels;

    // get values for the context and model
    public BankInfo_RecyclerViewAdapter (Context context, ArrayList<BankInfoModel> bankInfoModels) {
        this.context = context;
        this.bankInfoModels = bankInfoModels;
    }

    @NonNull
    @Override
    public BankInfo_RecyclerViewAdapter.BankInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.home_your_financials_row, parent, false);

        return new BankInfo_RecyclerViewAdapter.BankInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BankInfo_RecyclerViewAdapter.BankInfoViewHolder holder, int position) {
        // assigning values to the views created in the "home_your_financials_row" layout file
        // based on the position of the recycler view

        // TODO: add bank type (Checking, Savings, etc.) and image
        holder.tv_bankName.setText(bankInfoModels.get(position).getBankName());
        holder.tv_accountTotal.setText(bankInfoModels.get(position).getAccountTotal());
        // holder.imageView.setImageResource(bankInfoModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return bankInfoModels.size();
    }


    public static class BankInfoViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tv_bankName, tv_accountTotal;

        // getting the views from "home_your_financials_row" file and assigning to vars
        // Kinda like the onCreate method
        public BankInfoViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv_home_yourFinancialsBankImage);
            tv_bankName = itemView.findViewById(R.id.tv_home_yourFinancialsBankAccount);
            tv_accountTotal = itemView.findViewById(R.id.tv_home_yourFinancialsBankTotal);
        }
    }
}
