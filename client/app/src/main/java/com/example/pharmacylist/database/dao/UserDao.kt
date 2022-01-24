package com.example.pharmacylist.database.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pharmacylist.model.User

@Dao
interface UserDao{
    @Insert
    fun insert(user: User)

    @Query("DELETE FROM user_table")
    fun clear()

    @Query("SELECT * FROM user_table")
    fun getAllUsers(): List<User>

}