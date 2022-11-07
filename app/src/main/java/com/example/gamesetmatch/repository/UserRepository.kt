package com.example.gamesetmatch.repository

import androidx.lifecycle.LiveData
import com.example.gamesetmatch.dao.UserDao
import com.example.gamesetmatch.data.User

class UserRepository(private val userDao: UserDao){

    val allUsers: LiveData<List<User>> = userDao.getAllUser()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}