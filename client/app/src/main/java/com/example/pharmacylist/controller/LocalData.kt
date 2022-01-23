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

    /** initial database **/
    private fun initDataBase(application: Application){
        userDatabase = PharmacyListDataBase.getInstance(application)?.UserDao()!!
        consultationDatabase = PharmacyListDataBase.getInstance(application)?.ConsultationDao()!!
    }
    /** Insert pharmacists (users) in database if table is not empty -> clear table and insert them  **/
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
    /** get all pharmacists from table of Users -database **/
    fun fetchUsers(application: Application): List<User> {
        initDataBase(application)
        return userDatabase.getAllUsers()
    }

    /** clear table of consultation in database and get all consultation from sever and put them in database **/
    fun insertConsultationsOnline(application: Application, consultations: List<Consultation>){
        initDataBase(application)
        if(!consultationDatabase.getAllConsultations().isNullOrEmpty()){
            clearConsultationTable(application)
            for (consultation in consultations){
                consultation.isSend=true
                consultationDatabase.insert(consultation)
            }
        }else{
            for (consultation in consultations){
                consultation.isSend=true
                consultationDatabase.insert(consultation)
            }
        }

    }
    /** Insert consultation in database **/
    fun insertConsultationOffline(application: Application, consultation: Consultation){
        initDataBase(application)
        consultationDatabase.insert(consultation)
    }
    /** get list of consultation didn't send **/
    fun getConsultationNotSending(application: Application):List<Consultation>?{
        initDataBase(application)
        return consultationDatabase.getAllConsultations().filter { consultation -> !consultation.isSend }
    }

    /** get list of consultation from database **/
    fun getAllConsultation(application: Application):List<Consultation>?{
        initDataBase(application)
        return consultationDatabase.getAllConsultations()
    }


    /** Clear consultation table  **/
     fun clearConsultationTable(application: Application){
         initDataBase(application)
        consultationDatabase.clear()
    }



}