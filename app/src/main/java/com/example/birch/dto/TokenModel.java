package com.example.birch.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.reactivex.rxjava3.core.Single;

public class TokenModel {
    @SerializedName("link_token")
    @Expose
    private String link_token;

    @SerializedName("expiration")
    @Expose
    private String expiration;

    @SerializedName("request_id")
    @Expose
    private String request_id;

    public String getLink_token(){
        return link_token;
    }

    public void setLink_token(){
        this.link_token = link_token;
    }

    public String getExpiration(){
        return expiration;
    }

    public void setExpiration(){
        this.expiration = expiration;
    }

    public String getRequest_id(){
        return request_id;
    }

    public void setRequest_id(){
        this.request_id = request_id;
    }

    @Override
    public String toString(){
        return "ClassPojo [link_token = "+link_token+", expiration ="+expiration+", request_id ="+request_id+"]";
    }
}
