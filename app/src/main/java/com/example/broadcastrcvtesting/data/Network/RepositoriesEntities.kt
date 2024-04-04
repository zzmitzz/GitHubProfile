package com.example.broadcastrcvtesting.data.Network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepositoriesEntities(
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val description: String,
    val fork: Boolean,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("forks_count")
    val forksCount: Int
) : Serializable