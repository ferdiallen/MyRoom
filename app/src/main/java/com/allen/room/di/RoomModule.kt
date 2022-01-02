package com.allen.room.di

import android.content.Context
import androidx.room.Room
import com.allen.room.DBDAO
import com.allen.room.Databases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun providesDAO(db: Databases): DBDAO {
        return db.dbDao()
    }

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): Databases {
        return Room.databaseBuilder(
            context, Databases::class.java, "db_main"
        ).build()
    }
}