package com.example.birch.Liabilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiabilitiesModel {
    @SerializedName("liabilities")
    @Expose
    private Liabilities liabilities;

    @SerializedName("request_id")
    @Expose
    private String request_id;

    public Liabilities getLiabilities ()
    {
        return liabilities;
    }

    public void setLiabilities (Liabilities liabilities)
    {
        this.liabilities = liabilities;
    }

    public String getRequest_id ()
    {
        return request_id;
    }

    public void setRequest_id (String request_id)
    {
        this.request_id = request_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [liabilities = "+liabilities+", request_id = "+request_id+"]";
    }
}
