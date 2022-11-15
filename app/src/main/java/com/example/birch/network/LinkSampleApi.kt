package com.example.birch.network

import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.core.Single
import retrofit2.http.POST

/**
 * API calls to our localhost token server.
 */
interface LinkSampleApi {

  @POST("/api/create_link_token")
  fun getLinkToken(): Single<LinkToken>
  @POST("/api/set_access_token")
  fun getAccToken(temp: String): Single<AccToken>
}

data class LinkToken(
  @SerializedName("link_token") val link_token: String
)
//data class AccToken(
//  @SerializedName("acc_token") val acc_token: String
//)
data class AccToken(
  @SerializedName("acc_token") val acc_token: String
)

