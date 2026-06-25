package com.ubayadev.pejuangsubuh.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    fun checkLogin(username: String, password: String): User?
}