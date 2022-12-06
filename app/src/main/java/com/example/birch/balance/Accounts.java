package com.example.birch.balance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Accounts {
    @SerializedName("official_name")
    @Expose
    private String official_name;

    @SerializedName("balances")
    @Expose
    private Balances balances;

    @SerializedName("account_id")
    @Expose
    private String account_id;

    @SerializedName("subtype")
    @Expose
    private String subtype;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("mask")
    @Expose
    private String mask;

    public String getOfficial_name () {
        return official_name;
    }

    public void setOfficial_name (String official_name) {
        this.official_name = official_name;
    }

    public Balances getBalances () {
        return balances;
    }

    public void setBalances (Balances balances) {
        this.balances = balances;
    }

    public String getAccount_id () {
        return account_id;
    }

    public void setAccount_id (String account_id) {
        this.account_id = account_id;
    }

    public String getSubtype () {
        return subtype;
    }

    public void setSubtype (String subtype) {
        this.subtype = subtype;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getType () {
        return type;
    }

    public void setType (String type) {
        this.type = type;
    }

    public String getMask () {
        return mask;
    }

    public void setMask (String mask) {
        this.mask = mask;
    }

    @Override
    public String toString() {
        return "ClassPojo [official_name = "+official_name+", balances = "+balances+", account_id = "+account_id+", subtype = "+subtype+", name = "+name+", type = "+type+", mask = "+mask+"]";
    }
}
