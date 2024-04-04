package com.example.broadcastrcvtesting.data.models

import com.example.broadcastrcvtesting.core.cvtToUserEntity
import com.example.broadcastrcvtesting.core.map
import com.example.broadcastrcvtesting.data.Network.GithubProfileEntities
import com.example.broadcastrcvtesting.network.ServiceLocator
import com.example.broadcastrcvtesting.roomDB.RoomDataBase
import com.google.gson.Gson
import kotlinx.coroutines.delay
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.time.measureTime

interface GithubProfilerRepo {
    public fun cvtToGithubProfiler(entities: GithubProfileEntities): GithubProfiler?
    suspend fun getUserGithub(username: String): GithubProfiler?

    class Network() : GithubProfilerRepo {
        override fun cvtToGithubProfiler(a: GithubProfileEntities): GithubProfiler? {
            return GithubProfiler(
                a.login,
                a.avatarUrl,
                a.followers,
                a.following,
                a.bio,
                a.name,
                a.reposUrl
            )
        }
        override suspend fun getUserGithub(username: String): GithubProfiler? {
            var result: GithubProfiler? = null
            ServiceLocator.apiService.getUserProfile(username).enqueue(
                object : Callback<ResponseBody> {
                    override fun onResponse(p0: Call<ResponseBody>, p1: Response<ResponseBody>) {
                        try{
                            val gson = Gson()
                            var jsonString = p1.body()?.string().toString()
                            if(jsonString == "null"){
                                result = GithubProfiler.default
                            }else{
                                var test = gson.fromJson(jsonString, GithubProfileEntities::class.java)
                                result = cvtToGithubProfiler(test)
                            }

                        }catch (e: Exception){
                        }
                    }
                    override fun onFailure(p0: Call<ResponseBody>, p1: Throwable) {
                    }
                })
            val a = measureTime {
                while(result == null){
                    delay(50)
                }
            }
            return result
        }

    }
    class RoomDB(private val database: RoomDataBase){
        suspend fun getAllUser(): List<GithubProfiler>{
            val list = database.userDao().queryAllUser()
            return list.map { it.map(it) }
        }
        suspend fun insertUser(user: GithubProfiler): Int{
            return database.userDao().insert(user.cvtToUserEntity(user))
        }
    }
}


