package com.example.broadcastrcvtesting.core

import com.example.broadcastrcvtesting.data.models.GithubProfiler
import com.example.broadcastrcvtesting.roomDB.models.UserEntity

internal fun UserEntity.map(a : UserEntity) : GithubProfiler {
        return GithubProfiler(
            a.login,
            a.avatar_url,
            a.followers,
            a.following,
            a.bio,
            a.name,
            a.repos_url
        )
    }
internal fun GithubProfiler.cvtToUserEntity(a : GithubProfiler) : UserEntity {
        return UserEntity(
            login = a.login,
            avatar_url = a.avatar_url,
            followers = a.followers,
            following = a.following,
            bio = a.bio,
            name = a.name,
            repos_url = a.repos_url
        )
    }