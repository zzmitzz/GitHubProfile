package com.example.broadcastrcvtesting.roomDB.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.broadcastrcvtesting.roomDB.models.UserEntity

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity): Int // return the id

    @Query("Select * from users where login like :username")
    suspend fun queryUserByUsername(username: String): UserEntity
    @Query("Select * from users")
    suspend fun queryAllUser(): List<UserEntity>
}