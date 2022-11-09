package com.example.gamesetmatch.repository

import androidx.lifecycle.LiveData
import com.example.gamesetmatch.dao.PlayerDao
import com.example.gamesetmatch.data.Player

class PlayerRepository(private val playerDao: PlayerDao) {

    val allPlayers: LiveData<List<Player>> = playerDao.getAllPlayers()

    suspend fun addPlayer(player: Player){
        playerDao.addPlayer(player)
    }
}