package com.example.broadcastrcvtesting.network

import com.example.broadcastrcvtesting.data.Network.RepositoriesEntities
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface APIInterface {
    @GET("users/{username}")
    fun getUserProfile(@Path("username") username: String): Call<ResponseBody>

    @GET("users/{username}/repos")
    fun getUserRepos(@Path("username") username: String): Call<List<RepositoriesEntities>>
    companion object{
        fun retrofitService(retrofit: Retrofit) : APIInterface{
            return retrofit.create(APIInterface::class.java)
        }
    }
}