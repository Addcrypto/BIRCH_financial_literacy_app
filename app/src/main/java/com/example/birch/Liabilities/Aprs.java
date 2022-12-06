package com.example.birch.Liabilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Aprs {
    @SerializedName("apr_percentage")
    @Expose
    private String apr_percentage;

    @SerializedName("apr_type")
    @Expose
    private String apr_type;

    @SerializedName("interest_charge_amount")
    @Expose
    private String interest_charge_amount;

    @SerializedName("balance_subject_to_apr")
    @Expose
    private String balance_subject_to_apr;

    public String getApr_percentage ()
    {
        return apr_percentage;
    }

    public void setApr_percentage (String apr_percentage)
    {
        this.apr_percentage = apr_percentage;
    }

    public String getApr_type ()
    {
        return apr_type;
    }

    public void setApr_type (String apr_type)
    {
        this.apr_type = apr_type;
    }

    public String getInterest_charge_amount ()
    {
        return interest_charge_amount;
    }

    public void setInterest_charge_amount (String interest_charge_amount)
    {
        this.interest_charge_amount = interest_charge_amount;
    }

    public String getBalance_subject_to_apr ()
    {
        return balance_subject_to_apr;
    }

    public void setBalance_subject_to_apr (String balance_subject_to_apr)
    {
        this.balance_subject_to_apr = balance_subject_to_apr;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [apr_percentage = "+apr_percentage+", apr_type = "+apr_type+", interest_charge_amount = "+interest_charge_amount+", balance_subject_to_apr = "+balance_subject_to_apr+"]";
    }
}
