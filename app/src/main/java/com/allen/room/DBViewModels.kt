package com.allen.room

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DBViewModels @Inject constructor(
    private val repo: Repo
) : ViewModel() {
    var collected = repo.getAlls()
        private set

    fun tambahUsers(users: DB) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.InsertUsers(users)
        }
    }

    fun deleteUser(user: DB) {
        viewModelScope.launch {
            repo.deleteUsers(user)
        }
    }

    fun deleteUserByName(name: String) {

    }
}