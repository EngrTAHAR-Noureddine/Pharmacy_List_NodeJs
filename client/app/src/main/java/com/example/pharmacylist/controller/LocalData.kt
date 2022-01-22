package com.example.pharmacylist.controller

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.pharmacylist.database.PharmacyListDataBase
import com.example.pharmacylist.database.dao.ConsultationDao
import com.example.pharmacylist.database.dao.UserDao
import com.example.pharmacylist.model.Consultation
import com.example.pharmacylist.model.User


object LocalData {
    private lateinit var userDatabase: UserDao
    private lateinit var consultationDatabase: ConsultationDao

    private fun initDataBase(application: Application){
        userDatabase = PharmacyListDataBase.getInstance(application)?.UserDao()!!
        consultationDatabase = PharmacyListDataBase.getInstance(application)?.ConsultationDao()!!
    }

    fun insertUsers(application: Application, users: List<User>){
        initDataBase(application)

         if(!userDatabase.getAllUsers().isNullOrEmpty()){
             userDatabase.clear()
             for (user in users){
                 userDatabase.insert(user)
             }
         }else{
             for (user in users){
                 userDatabase.insert(user)
             }
         }
     }
    fun fetchUsers(application: Application): List<User> {
        initDataBase(application)
        return userDatabase.getAllUsers()
    }

    fun insertConsultationsOnline(application: Application, consultations: List<Consultation>){
        initDataBase(application)
        if(!consultationDatabase.getAllConsultations().isNullOrEmpty()){
            clearConsultationTable(application)
            for (consultation in consultations){
                consultationDatabase.insert(consultation)
            }
        }else{
            for (consultation in consultations){
                consultationDatabase.insert(consultation)
            }
        }

    }

    fun insertConsultationsOffline(application: Application, consultation: Consultation){
        initDataBase(application)
        consultationDatabase.insert(consultation)
    }

    fun getConsultationNotSending(application: Application):List<Consultation>?{
        initDataBase(application)
        return consultationDatabase.getAllConsultations().filter { consultation -> !consultation.isSend }
    }

    fun getAllConsultation(application: Application):List<Consultation>?{
        initDataBase(application)
        return consultationDatabase.getAllConsultations()
    }



     fun clearConsultationTable(application: Application){
         initDataBase(application)
        consultationDatabase.clear()
    }



}