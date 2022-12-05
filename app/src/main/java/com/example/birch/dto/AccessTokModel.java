package com.example.birch.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccessTokModel {
    @SerializedName("access_token")
    @Expose
    private String access_token;

    @SerializedName("item_id")
    @Expose
    private String item_id;

    @SerializedName("request_id")
    @Expose
    private String request_id;

    public String getAccess_token ()
    {
        return access_token;
    }

    public void setAccess_token (String access_token)
    {
        this.access_token = access_token;
    }

    public String getItem_id ()
    {
        return item_id;
    }

    public void setItem_id (String item_id)
    {
        this.item_id = item_id;
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
        return "ClassPojo [access_token = "+access_token+", item_id = "+item_id+", request_id = "+request_id+"]";
    }
}
