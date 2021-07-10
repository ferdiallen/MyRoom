package com.allen.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DBDAO {
    @Query("SELECT*FROM Users")
     fun getAll():LiveData<List<DB>>

    @Delete
    suspend fun deleteData(users:DB)

    @Insert
    suspend fun InsertData(users:DB)

    @Update
    suspend fun UpdateData(users: DB)
}