package com.example.pharmacylist.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pharmacylist.database.models.Consultation


@Dao
interface ConsultationDao {
    @Insert
    suspend fun insert(consultation: Consultation)

    @Query("DELETE FROM consultation_table")
    suspend fun clear()

    @Query("SELECT * FROM consultation_table")
    fun getAllUsers(): LiveData<List<Consultation>>

    @Update
    fun updateConsultation(consultation: Consultation)

}