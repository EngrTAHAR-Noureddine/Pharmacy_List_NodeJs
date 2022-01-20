package com.example.pharmacylist.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pharmacylist.database.models.User

@Dao
interface UserDao{
    @Insert
    suspend fun insert(user: User)

    @Query("DELETE FROM user_table")
    suspend fun clear()

    @Query("SELECT * FROM user_table")
    fun getAllUsers(): LiveData<List<User>>

}