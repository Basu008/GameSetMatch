package com.example.gamesetmatch.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.gamesetmatch.data.Player
import com.example.gamesetmatch.data.User
import com.example.gamesetmatch.database.PlayerDatabase
import com.example.gamesetmatch.database.UserDatabase
import com.example.gamesetmatch.repository.PlayerRepository
import com.example.gamesetmatch.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerViewModel(application: Application): AndroidViewModel(application) {

    val allPlayers: LiveData<List<Player>>
    private val repository: PlayerRepository

    init {
        val playerDao = PlayerDatabase.getDatabase(application).playerDao()
        repository = PlayerRepository(playerDao)
        allPlayers = repository.allPlayers
    }

    fun addUser(player: Player){
        viewModelScope.launch(Dispatchers.IO){
            repository.addPlayer(player)
        }
    }
}