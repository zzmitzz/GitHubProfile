package com.example.broadcastrcvtesting.data.models

import android.util.Log
import com.example.broadcastrcvtesting.data.Network.RepositoriesEntities
import com.example.broadcastrcvtesting.network.ServiceLocator
import com.google.gson.Gson
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface RepositoryRepo {
    public fun cvtToRepositoryModel(entities: RepositoriesEntities): RepositoryModel?
    suspend fun getRepos(username: String): List<RepositoryModel?>

    class Network() : RepositoryRepo {
        private var list = mutableListOf<RepositoryModel>()
        override fun cvtToRepositoryModel(entities: RepositoriesEntities): RepositoryModel? {
            return RepositoryModel(
                entities.name,
                entities.fullName,
                entities.stargazersCount,
                entities.htmlUrl,
                entities.description,
                entities.fork,
                entities.forksCount
            )
        }

        override suspend fun getRepos(username: String): List<RepositoryModel> {
            ServiceLocator.apiService.getUserRepos(username).enqueue(object : Callback<List<RepositoriesEntities>> {
                override fun onResponse(
                    p0: Call<List<RepositoriesEntities>>,
                    p1: Response<List<RepositoriesEntities>>
                ) {
                    try {
                        val gson = Gson()
                        var listRepos = p1.body()
                        if (listRepos != null) {
                            for(x in listRepos){
                                var test = cvtToRepositoryModel(x)
                                Log.d("RepositoryRepo", "${test!!.name}" + test!!.stargazersCount.toString())
                                list.add(test!!)
                            }
                        }
                    }catch (e: Exception){}
                }
                override fun onFailure(p0: Call<List<RepositoriesEntities>>, p1: Throwable) {
                }

            })
            delay(1000)
            return list
        }

    }
}