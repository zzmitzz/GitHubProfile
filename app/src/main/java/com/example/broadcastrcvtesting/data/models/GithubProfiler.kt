package com.example.broadcastrcvtesting.data.models

data class GithubProfiler(
    var login: String,
    var avatar_url: String,
    var followers: Int,
    var following: Int,
    var bio: String,
    var name: String,
    var repos_url: String
){
    companion object{
        val default = GithubProfiler(
            "","",0,0,"","","",
        )
    }
}