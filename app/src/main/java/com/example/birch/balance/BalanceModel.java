package com.example.birch.balance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BalanceModel {
    @SerializedName("item")
    @Expose
    private Item item;

    @SerializedName("accounts")
    @Expose
    private Accounts[] accounts;

    @SerializedName("request_id")
    @Expose
    private String request_id;

    public Item getItem () {
        return item;
    }

    public void setItem (Item item) {
        this.item = item;
    }

    public Accounts[] getAccounts () {
        return accounts;
    }

    public void setAccounts (Accounts[] accounts) {
        this.accounts = accounts;
    }

    public String getRequest_id () {
        return request_id;
    }

    public void setRequest_id (String request_id) {
        this.request_id = request_id;
    }

    @Override
    public String toString() {
        return "ClassPojo [item = "+item+", accounts = "+accounts+", request_id = "+request_id+"]";
    }
}
