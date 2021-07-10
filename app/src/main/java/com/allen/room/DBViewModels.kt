package com.allen.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DBViewModels(application: Application) : AndroidViewModel(application) {
    private val repos:Repo
     var readAllData: LiveData<List<DB>>

    init {
        val usersDB = Databases.getDB(application).dbDao()
        repos = Repo(usersDB)
        readAllData = repos.getAlls()
    }
    fun tambahUsers(users:DB){
        viewModelScope.launch(Dispatchers.IO) {
            repos.InsertUsers(users)

        }
    }
    fun bacaData(){
        readAllData.value
    }
}