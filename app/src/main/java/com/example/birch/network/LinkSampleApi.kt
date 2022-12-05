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
  fun getAccToken(publicToken: PublicToken): Single<AccToken>
  @POST("/api/get_transactions")
  fun getTransactions(publicToken: PublicToken): Single<Transactions>
  @POST("/api/balance")
  fun getBalance(accToken: AccToken): Single<Balance>
  @POST("/api/liabilities")
  fun getLiabilities(accessToken: AccToken): Single<Liabilities>
  @POST("/api/accounts")
  fun getAccount(accToken: AccToken): Single<Account>
}
data class ItemId(
  @SerializedName("item_id") val item_id: String
)
data class LinkToken(
  @SerializedName("link_token") val link_token: String
)
data class PublicToken(
  @SerializedName("public_token") val publicToken: String
)
data class AccToken(
  @SerializedName("acc_token") val acc_token: String
)
data class Account(
  @SerializedName("account") val account: List<Account>
)
data class Transactions(
  @SerializedName("transaction") val transactions: List<Transactions>
)
data class Balance(
  @SerializedName("balance") val balance: Int
)
data class Liabilities(
  @SerializedName("liabilities") val liabilities: String
)

//data class AccToken(
//  @SerializedName("acc_token") val acc_token: String
//)

