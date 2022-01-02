package com.allen.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface DBDAO {
    @Query("SELECT*FROM Users")
    fun getAll(): Flow<List<DB>>

    @Delete
    suspend fun deleteData(users: DB)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun InsertData(users: DB)

    @Update
    suspend fun UpdateData(users: DB)

    @Query("SELECT * FROM Users WHERE nama_awal LIKE :namefirst")
    fun getUserByName(namefirst: String): List<DB>

    @Query("DELETE FROM USERS WHERE nama_awal LIKE :name")
    suspend fun deleteByName(name: String)
}