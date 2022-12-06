package com.example.birch.balance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Balances {
    @SerializedName("unofficial_currency_code")
    @Expose
    private String unofficial_currency_code;

    @SerializedName("current")
    @Expose
    private String current;

    @SerializedName("available")
    @Expose
    private String available;

    @SerializedName("iso_currency_code")
    @Expose
    private String iso_currency_code;

    @SerializedName("limit")
    @Expose
    private String limit;

    public String  getUnofficial_currency_code () {
        return unofficial_currency_code;
    }

    public void setUnofficial_currency_code (String unofficial_currency_code) {
        this.unofficial_currency_code = unofficial_currency_code;
    }

    public String getCurrent () {
        return current;
    }

    public void setCurrent (String current) {
        this.current = current;
    }

    public String getAvailable () {
        return available;
    }

    public void setAvailable (String available) {
        this.available = available;
    }

    public String getIso_currency_code () {
        return iso_currency_code;
    }

    public void setIso_currency_code (String iso_currency_code) {
        this.iso_currency_code = iso_currency_code;
    }

    public String getLimit () {
        return limit;
    }

    public void setLimit (String  limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "ClassPojo [unofficial_currency_code = "+unofficial_currency_code+", current = "+current+", available = "+available+", iso_currency_code = "+iso_currency_code+", limit = "+limit+"]";
    }
}
