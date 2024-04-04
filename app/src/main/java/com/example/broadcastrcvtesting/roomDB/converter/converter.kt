package com.example.broadcastrcvtesting.roomDB.converter

import androidx.room.TypeConverter
import java.util.Date


class Converter {
    @TypeConverter
    fun convertDateToIne(date: Date) : Long{
        return date.time
    }
}