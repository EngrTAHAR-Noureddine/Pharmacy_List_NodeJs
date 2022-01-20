package com.example.pharmacylist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pharmacylist.database.dao.ConsultationDao
import com.example.pharmacylist.database.dao.UserDao
import com.example.pharmacylist.database.models.Consultation
import com.example.pharmacylist.database.models.User


@Database(entities = [User::class,Consultation::class], version = 1, exportSchema = false)
abstract class PharmacyListDataBase: RoomDatabase() {

    abstract fun UserDao() : UserDao
    abstract fun ConsultationDao() : ConsultationDao


    companion object {
        private var INSTANCE: PharmacyListDataBase? = null

        fun getInstance(context: Context): PharmacyListDataBase? {
            if (INSTANCE == null) {
                synchronized(PharmacyListDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        PharmacyListDataBase::class.java, "pharmacyDatabase.db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}