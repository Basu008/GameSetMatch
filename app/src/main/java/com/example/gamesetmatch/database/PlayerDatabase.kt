package com.example.gamesetmatch.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gamesetmatch.dao.PlayerDao
import com.example.gamesetmatch.data.Player

@Database(entities = [Player::class], version = 2, exportSchema = true)
abstract class PlayerDatabase: RoomDatabase() {

    abstract fun playerDao(): PlayerDao

    companion object{
        @Volatile
        private var INSTANCE: PlayerDatabase? = null

        fun getDatabase(context: Context): PlayerDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null)
                return tempInstance
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlayerDatabase::class.java,
                    "player_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}