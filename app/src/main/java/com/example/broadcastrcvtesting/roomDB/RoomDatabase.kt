package com.example.broadcastrcvtesting.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.broadcastrcvtesting.roomDB.daos.UserDAO
import com.example.broadcastrcvtesting.roomDB.models.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class RoomDataBase : RoomDatabase() {
    abstract fun userDao(): UserDAO
    companion object {
        private const val DB_NAME = "users.db"
        @Volatile
        private var INSTANCE: RoomDataBase? = null
        fun getInstance(context: Context) : RoomDataBase{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: Room.databaseBuilder(context.applicationContext,RoomDataBase::class.java,
                    DB_NAME).build().also { INSTANCE = it }
            }
        }
    }

}