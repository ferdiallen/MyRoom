package com.allen.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [DB::class],version = 1,exportSchema = false)
abstract class Databases : RoomDatabase() {
    abstract fun dbDao():DBDAO
}