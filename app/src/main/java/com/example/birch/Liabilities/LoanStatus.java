package com.example.birch.Liabilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoanStatus {
    @SerializedName("end_date")
    @Expose
    private String end_date;

    @SerializedName("type")
    @Expose
    private String type;

    public String getEnd_date ()
    {
        return end_date;
    }

    public void setEnd_date (String end_date)
    {
        this.end_date = end_date;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [end_date = "+end_date+", type = "+type+"]";
    }
}
