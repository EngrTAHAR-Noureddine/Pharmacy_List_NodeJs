package com.example.pharmacylist.database.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pharmacylist.model.Consultation


@Dao
interface ConsultationDao {
    @Insert
    fun insert(consultation: Consultation)

    @Query("DELETE FROM consultation_table")
    fun clear()

    @Query("SELECT * FROM consultation_table")
    fun getAllConsultations(): List<Consultation>

    @Update
    fun updateConsultation(consultation: Consultation)

}