package com.example.birch.models;

// TODO: update this when API is set up
public class BankInfoModel {
    String bankName;
    String accountTotal;

    // checking or savings
    String accountType;

    public BankInfoModel(String bankName, String accountTotal, String accountType) {
        this.bankName = bankName;
        this.accountTotal = accountTotal;
        this.accountType = accountType;
    }

    public String getBankName() {
        return bankName;
    }

    public String getAccountTotal() {
        return accountTotal;
    }

    public String getAccountType() {
        return accountType;
    }
}
