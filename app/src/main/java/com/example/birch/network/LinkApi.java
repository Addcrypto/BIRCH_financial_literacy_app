package com.example.birch.network;

import com.example.birch.dto.AccessTokModel;
import com.example.birch.dto.TokenModel;


import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LinkApi {
    @POST("/api/create_link_token")
    Call<TokenModel> getLinkToken();

    @FormUrlEncoded
    @POST("/api/set_access_token")
    Call<AccessTokModel> getAccToken(@Field("public_token") String publicToken);

    @FormUrlEncoded
    @POST("/api/auth")
    Call
}
