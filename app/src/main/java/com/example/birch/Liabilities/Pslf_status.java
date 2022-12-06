package com.example.birch.Liabilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pslf_status {
    @SerializedName("payments_remaining")
    @Expose
    private String payments_remaining;

    @SerializedName("payments_made")
    @Expose
    private String payments_made;

    @SerializedName("estimated_eligibility_date")
    @Expose
    private String estimated_eligibility_date;

    public String getPayments_remaining ()
    {
        return payments_remaining;
    }

    public void setPayments_remaining (String payments_remaining)
    {
        this.payments_remaining = payments_remaining;
    }

    public String getPayments_made ()
    {
        return payments_made;
    }

    public void setPayments_made (String payments_made)
    {
        this.payments_made = payments_made;
    }

    public String getEstimated_eligibility_date ()
    {
        return estimated_eligibility_date;
    }

    public void setEstimated_eligibility_date (String estimated_eligibility_date)
    {
        this.estimated_eligibility_date = estimated_eligibility_date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [payments_remaining = "+payments_remaining+", payments_made = "+payments_made+", estimated_eligibility_date = "+estimated_eligibility_date+"]";
    }
}
