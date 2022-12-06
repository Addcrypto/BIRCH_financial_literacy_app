package com.example.birch.network;


import com.example.birch.Liabilities.LiabilitiesModel;
import com.example.birch.Transactions.TransactionsModel;
import com.example.birch.balance.BalanceModel;
import com.example.birch.dto.AccessTokModel;
import com.example.birch.dto.TokenModel;


import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LinkApi {
    @POST("api/create_link_token")
    Call<TokenModel> getLinkToken();

    @FormUrlEncoded
    @POST("api/set_access_token")
    Call<AccessTokModel> getAccToken(@Field("public_token") String publicToken);

    @GET("api/balance")
    Call<BalanceModel> getBalance(@Query("access_token") String accessToken);

    @GET("api/transactions")
    Call<TransactionsModel> getTransactions(@Query("access_token") String accessToken);

    @GET("/api/liabilities")
    Call<LiabilitiesModel> getLiabilities(@Query("access_token") String accessToken);
}
