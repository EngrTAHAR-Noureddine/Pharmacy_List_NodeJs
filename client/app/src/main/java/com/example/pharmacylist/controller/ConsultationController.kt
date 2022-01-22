package com.example.pharmacylist.controller

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.pharmacylist.Service.RetrofitServices
import com.example.pharmacylist.controller.LocalData.clearConsultationTable
import com.example.pharmacylist.controller.LocalData.getAllConsultation
import com.example.pharmacylist.controller.LocalData.getConsultationNotSending
import com.example.pharmacylist.controller.LocalData.insertConsultationsOffline
import com.example.pharmacylist.controller.LocalData.insertConsultationsOnline
import com.example.pharmacylist.model.Consultation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ConsultationController {

    var allConsultations = MutableLiveData<List<Consultation>?>()

    fun fetchAllConsultations(application: Application) {
        val data = MutableLiveData<List<Consultation>?>()

        val call = RetrofitServices.endpoint.fetchAllConsultations()

        call.enqueue(object : Callback<List<Consultation>>{


            override fun onResponse(
                call: Call<List<Consultation>>,
                response: Response<List<Consultation>>
            ) {

                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    data.value = res
                    insertConsultationsOnline(application,res)
                }else{
                    data.value = getAllConsultation(application)
                }
            }

            override fun onFailure(call: Call<List<Consultation>>, t: Throwable) {
                data.value = getAllConsultation(application)
            }
        })
        allConsultations = data
    }

    fun sendUnsendConsultation(context:Context){

        val listConsultation = getConsultationNotSending(context as Application)
        if(!listConsultation.isNullOrEmpty()){
            val index = MutableLiveData<Int>()
            index.value = 0
            for (consultation in listConsultation){
                val call = RetrofitServices.endpoint.setConsultation(consultation)

                call.enqueue(object : Callback<Consultation> {

                    override fun onFailure(call: Call<Consultation>, t: Throwable) {
                        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                    }
                    override fun onResponse(call: Call<Consultation>, response: Response<Consultation>) {
                        if (response.isSuccessful) {
                            index.value =  index.value!! + 1
                            Toast.makeText(context, "send Consultation was Successfully", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "insert Locally", Toast.LENGTH_SHORT).show()
                        }
                    }

                })
            }

            if( index.value!! >= listConsultation.size){
                clearConsultationTable(context)
            }
        }

    }

    fun consultationFun(consultation: Consultation, context:Context){

        val call = RetrofitServices.endpoint.setConsultation(consultation)

        call.enqueue(object : Callback<Consultation> {

            override fun onFailure(call: Call<Consultation>, t: Throwable) {
                insertConsultationsOffline(context as Application,consultation)
                Toast.makeText(context, "Error! insert Locally", Toast.LENGTH_SHORT)
                    .show()
            }
            override fun onResponse(call: Call<Consultation>, response: Response<Consultation>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "send Consultation was Successfully", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    insertConsultationsOffline(context as Application,consultation)
                    Toast.makeText(context, "insert Locally", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}