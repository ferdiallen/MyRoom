package com.allen.room

import androidx.lifecycle.LiveData

class Repo(private val dao:DBDAO) {
    fun getAlls(): LiveData<List<DB>> = dao.getAll()

    suspend fun InsertUsers(users:DB) = dao.InsertData(users)

    suspend fun updateUsers(users:DB) = dao.UpdateData(users)

    suspend fun deletUsers(users: DB) = dao.deleteData(users)
}