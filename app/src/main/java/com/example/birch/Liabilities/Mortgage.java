package com.example.birch.Liabilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mortgage {
    @SerializedName("origination_principal_amount")
    @Expose
    private String origination_principal_amount;

    @SerializedName("account_number")
    @Expose
    private String account_number;

    @SerializedName("next_monthly_payment")
    @Expose
    private String next_monthly_payment;

    @SerializedName("loan_type_description")
    @Expose
    private String loan_type_description;

    @SerializedName("escrow_balance")
    @Expose
    private String escrow_balance;

    @SerializedName("has_pmi")
    @Expose
    private String has_pmi;

    @SerializedName("last_payment_date")
    @Expose
    private String last_payment_date;

    @SerializedName("past_due_amount")
    @Expose
    private String past_due_amount;

    @SerializedName("maturity_date")
    @Expose
    private String maturity_date;

    @SerializedName("has_prepayment_penalty")
    @Expose
    private String has_prepayment_penalty;

    @SerializedName("current_late_fee")
    @Expose
    private String current_late_fee;

    @SerializedName("last_payment_amount")
    @Expose
    private String last_payment_amount;

    @SerializedName("property_address")
    @Expose
    private Property_address property_address;

    @SerializedName("account_id")
    @Expose
    private String account_id;

    @SerializedName("origination_date")
    @Expose
    private String origination_date;

    @SerializedName("loan_term")
    @Expose
    private String loan_term;

    @SerializedName("interest_rate")
    @Expose
    private InterestRate interest_rate;

    @SerializedName("ytd_interest_paid")
    @Expose
    private String ytd_interest_paid;

    @SerializedName("ytd_principal_paid")
    @Expose
    private String ytd_principal_paid;

    @SerializedName("next_payment_due_date")
    @Expose
    private String next_payment_due_date;

    public String getOrigination_principal_amount ()
    {
        return origination_principal_amount;
    }

    public void setOrigination_principal_amount (String origination_principal_amount)
    {
        this.origination_principal_amount = origination_principal_amount;
    }

    public String getAccount_number ()
    {
        return account_number;
    }

    public void setAccount_number (String account_number)
    {
        this.account_number = account_number;
    }

    public String getNext_monthly_payment ()
    {
        return next_monthly_payment;
    }

    public void setNext_monthly_payment (String next_monthly_payment)
    {
        this.next_monthly_payment = next_monthly_payment;
    }

    public String getLoan_type_description ()
    {
        return loan_type_description;
    }

    public void setLoan_type_description (String loan_type_description)
    {
        this.loan_type_description = loan_type_description;
    }

    public String getEscrow_balance ()
    {
        return escrow_balance;
    }

    public void setEscrow_balance (String escrow_balance)
    {
        this.escrow_balance = escrow_balance;
    }

    public String getHas_pmi ()
    {
        return has_pmi;
    }

    public void setHas_pmi (String has_pmi)
    {
        this.has_pmi = has_pmi;
    }

    public String getLast_payment_date ()
    {
        return last_payment_date;
    }

    public void setLast_payment_date (String last_payment_date)
    {
        this.last_payment_date = last_payment_date;
    }

    public String getPast_due_amount ()
    {
        return past_due_amount;
    }

    public void setPast_due_amount (String past_due_amount)
    {
        this.past_due_amount = past_due_amount;
    }

    public String getMaturity_date ()
    {
        return maturity_date;
    }

    public void setMaturity_date (String maturity_date)
    {
        this.maturity_date = maturity_date;
    }

    public String getHas_prepayment_penalty ()
    {
        return has_prepayment_penalty;
    }

    public void setHas_prepayment_penalty (String has_prepayment_penalty)
    {
        this.has_prepayment_penalty = has_prepayment_penalty;
    }

    public String getCurrent_late_fee ()
    {
        return current_late_fee;
    }

    public void setCurrent_late_fee (String current_late_fee)
    {
        this.current_late_fee = current_late_fee;
    }

    public String getLast_payment_amount ()
    {
        return last_payment_amount;
    }

    public void setLast_payment_amount (String last_payment_amount)
    {
        this.last_payment_amount = last_payment_amount;
    }

    public Property_address getProperty_address ()
    {
        return property_address;
    }

    public void setProperty_address (Property_address property_address)
    {
        this.property_address = property_address;
    }

    public String getAccount_id ()
    {
        return account_id;
    }

    public void setAccount_id (String account_id)
    {
        this.account_id = account_id;
    }

    public String getOrigination_date ()
    {
        return origination_date;
    }

    public void setOrigination_date (String origination_date)
    {
        this.origination_date = origination_date;
    }

    public String getLoan_term ()
    {
        return loan_term;
    }

    public void setLoan_term (String loan_term)
    {
        this.loan_term = loan_term;
    }

    public InterestRate getInterest_rate ()
    {
        return interest_rate;
    }

    public void setInterest_rate (InterestRate interest_rate)
    {
        this.interest_rate = interest_rate;
    }

    public String getYtd_interest_paid ()
    {
        return ytd_interest_paid;
    }

    public void setYtd_interest_paid (String ytd_interest_paid)
    {
        this.ytd_interest_paid = ytd_interest_paid;
    }

    public String getYtd_principal_paid ()
    {
        return ytd_principal_paid;
    }

    public void setYtd_principal_paid (String ytd_principal_paid)
    {
        this.ytd_principal_paid = ytd_principal_paid;
    }

    public String getNext_payment_due_date ()
    {
        return next_payment_due_date;
    }

    public void setNext_payment_due_date (String next_payment_due_date)
    {
        this.next_payment_due_date = next_payment_due_date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [origination_principal_amount = "+origination_principal_amount+", account_number = "+account_number+", next_monthly_payment = "+next_monthly_payment+", loan_type_description = "+loan_type_description+", escrow_balance = "+escrow_balance+", has_pmi = "+has_pmi+", last_payment_date = "+last_payment_date+", past_due_amount = "+past_due_amount+", maturity_date = "+maturity_date+", has_prepayment_penalty = "+has_prepayment_penalty+", current_late_fee = "+current_late_fee+", last_payment_amount = "+last_payment_amount+", property_address = "+property_address+", account_id = "+account_id+", origination_date = "+origination_date+", loan_term = "+loan_term+", interest_rate = "+interest_rate+", ytd_interest_paid = "+ytd_interest_paid+", ytd_principal_paid = "+ytd_principal_paid+", next_payment_due_date = "+next_payment_due_date+"]";
    }
}
