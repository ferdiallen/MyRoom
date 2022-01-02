package com.allen.room

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repo @Inject constructor(private val dao: DBDAO) {
    fun getAlls(): Flow<List<DB>> = dao.getAll()

    suspend fun InsertUsers(users: DB) = dao.InsertData(users)

    suspend fun updateUsers(users: DB) = dao.UpdateData(users)

    suspend fun deleteUsers(users: DB) = dao.deleteData(users)
}