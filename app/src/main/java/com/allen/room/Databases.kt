package com.allen.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [DB::class],version = 1,exportSchema = false)
abstract class Databases : RoomDatabase() {
    abstract fun dbDao():DBDAO
    companion object{
        @Volatile
        private var INST: Databases? = null

        fun getDB(context:Context):Databases{
            val tempt = INST
            if(tempt!=null){
                return tempt
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context,Databases::class.java,"room_db"
                ).build()
                INST=instance
                return instance
            }
        }
    }
}