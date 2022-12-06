package com.example.birch.Liabilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Credit {
    @SerializedName("account_id")
    @Expose
    private String account_id;

    @SerializedName("last_payment_date")
    @Expose
    private String last_payment_date;

    @SerializedName("is_overdue")
    @Expose
    private String is_overdue;

    @SerializedName("last_statement_issue_date")
    @Expose
    private String last_statement_issue_date;

    @SerializedName("minimum_payment_amount")
    @Expose
    private String minimum_payment_amount;

    @SerializedName("aprs")
    @Expose
    private Aprs[] aprs;

    @SerializedName("last_payment_amount")
    @Expose
    private String last_payment_amount;

    @SerializedName("next_payment_due_date")
    @Expose
    private String next_payment_due_date;

    @SerializedName("last_statement_balance")
    @Expose
    private String last_statement_balance;

    public String getAccount_id ()
    {
        return account_id;
    }

    public void setAccount_id (String account_id)
    {
        this.account_id = account_id;
    }

    public String getLast_payment_date ()
    {
        return last_payment_date;
    }

    public void setLast_payment_date (String last_payment_date)
    {
        this.last_payment_date = last_payment_date;
    }

    public String getIs_overdue ()
    {
        return is_overdue;
    }

    public void setIs_overdue (String is_overdue)
    {
        this.is_overdue = is_overdue;
    }

    public String getLast_statement_issue_date ()
    {
        return last_statement_issue_date;
    }

    public void setLast_statement_issue_date (String last_statement_issue_date)
    {
        this.last_statement_issue_date = last_statement_issue_date;
    }

    public String getMinimum_payment_amount ()
    {
        return minimum_payment_amount;
    }

    public void setMinimum_payment_amount (String minimum_payment_amount)
    {
        this.minimum_payment_amount = minimum_payment_amount;
    }

    public Aprs[] getAprs ()
    {
        return aprs;
    }

    public void setAprs (Aprs[] aprs)
    {
        this.aprs = aprs;
    }

    public String getLast_payment_amount ()
    {
        return last_payment_amount;
    }

    public void setLast_payment_amount (String last_payment_amount)
    {
        this.last_payment_amount = last_payment_amount;
    }

    public String getNext_payment_due_date ()
    {
        return next_payment_due_date;
    }

    public void setNext_payment_due_date (String next_payment_due_date)
    {
        this.next_payment_due_date = next_payment_due_date;
    }

    public String getLast_statement_balance ()
    {
        return last_statement_balance;
    }

    public void setLast_statement_balance (String last_statement_balance)
    {
        this.last_statement_balance = last_statement_balance;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [account_id = "+account_id+", last_payment_date = "+last_payment_date+", is_overdue = "+is_overdue+", last_statement_issue_date = "+last_statement_issue_date+", minimum_payment_amount = "+minimum_payment_amount+", aprs = "+aprs+", last_payment_amount = "+last_payment_amount+", next_payment_due_date = "+next_payment_due_date+", last_statement_balance = "+last_statement_balance+"]";
    }
}
