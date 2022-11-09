package com.example.gamesetmatch.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gamesetmatch.data.Player

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlayer(player: Player)

    @Query("SELECT * FROM player_table ORDER BY id")
    fun getAllPlayers(): LiveData<List<Player>>
}