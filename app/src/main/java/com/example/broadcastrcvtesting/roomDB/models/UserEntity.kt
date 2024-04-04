package com.example.broadcastrcvtesting.roomDB.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users", indices = [androidx.room.Index(value = ["login"])])
data class UserEntity (
    @PrimaryKey
    var login: String = "",
    var avatar_url: String = "",
    var followers: Int = 0,
    var following: Int = 0,
    var bio: String = "",
    var name: String = "",
    var repos_url: String = ""


)