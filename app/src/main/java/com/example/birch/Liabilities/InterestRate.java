package com.example.birch.Liabilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InterestRate {
    @SerializedName("percentage")
    @Expose
    private String percentage;

    @SerializedName("type")
    @Expose
    private String type;

    public String getPercentage ()
    {
        return percentage;
    }

    public void setPercentage (String percentage)
    {
        this.percentage = percentage;
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
        return "ClassPojo [percentage = "+percentage+", type = "+type+"]";
    }
}
