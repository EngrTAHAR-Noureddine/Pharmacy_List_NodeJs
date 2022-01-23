package com.example.pharmacylist.controller

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.work.*
import com.example.pharmacylist.Service.RetrofitServices
import com.example.pharmacylist.controller.LocalData.clearConsultationTable
import com.example.pharmacylist.controller.LocalData.getAllConsultation
import com.example.pharmacylist.controller.LocalData.getConsultationNotSending
import com.example.pharmacylist.controller.LocalData.insertConsultationOffline
import com.example.pharmacylist.controller.LocalData.insertConsultationsOnline
import com.example.pharmacylist.model.Consultation
import com.example.pharmacylist.workmanager.MyWorkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ConsultationController {

    /***
     * variable assign list of consultations
     *  ***/
    var allConsultations = MutableLiveData<List<Consultation>?>()
    /** unsending list of consultation **/
    var unsendingConsultationsList = MutableLiveData<List<Consultation>?>()

    /** Variable work manager **/
     private lateinit var  workManager : WorkManager
    /** Init  work manager **/
     private fun initWorkManager(application: Application){
        workManager = WorkManager.getInstance(application)
    }
    /** Cancel work manager **/
    fun cancelWorkManager(){
        workManager.cancelAllWorkByTag("SendUnsendConsultations");
    }
    /** Apply work manager **/
    fun applyWorkManager(application: Application){
        initWorkManager(application)
        // the containt of network didn't work, so i removed it
        val mRequest = OneTimeWorkRequest.Builder(MyWorkManager::class.java)
            .addTag("SendUnsendConsultations").build()
        workManager.enqueue(mRequest)
    }
    /** Void function to fetch consultation if internet available get from server else get from local data **/
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
                allConsultations.value = data.value
            }

            override fun onFailure(call: Call<List<Consultation>>, t: Throwable) {
                data.value = getAllConsultation(application)
                allConsultations.value = data.value
            }
        })

    }

    /** void function to get unsending Consultation list **/
    fun getListOfUnsendingConsultation(context: Context){
        unsendingConsultationsList.postValue(getConsultationNotSending(context as Application))
    }

    /** function to send unsending consultation to the server **/
    fun sendUnsendConsultation(context:Context){

        getListOfUnsendingConsultation(context)
        if(!unsendingConsultationsList.value.isNullOrEmpty()){
            var index =  0
            for (consultation in unsendingConsultationsList.value!!){
                val call = RetrofitServices.endpoint.setConsultation(consultation)

                call.enqueue(object : Callback<Consultation> {

                    override fun onFailure(call: Call<Consultation>, t: Throwable) {
                        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                    }
                    override fun onResponse(call: Call<Consultation>, response: Response<Consultation>) {
                        if (response.isSuccessful) {
                            index += 1
                            Toast.makeText(context, "send Consultation was Successfully", Toast.LENGTH_SHORT).show()
                            if(unsendingConsultationsList.value!![unsendingConsultationsList.value!!.size-1].consultation == consultation.consultation){
                                clearConsultationTable(context as Application)
                                fetchAllConsultations(context)
                            }
                        } else {
                            Toast.makeText(context, "insert Locally", Toast.LENGTH_SHORT).show()
                        }
                    }

                })
            }


        }

    }


    /** post consultation to the server and insert offline  **/
    fun consultationFun(consultation: Consultation, context:Context){

        val call = RetrofitServices.endpoint.setConsultation(consultation)

        call.enqueue(object : Callback<Consultation> {

            override fun onFailure(call: Call<Consultation>, t: Throwable) {
                consultation.isSend = false
                insertConsultationOffline(context as Application,consultation)
                Toast.makeText(context, "Error! insert Locally", Toast.LENGTH_SHORT)
                    .show()
                fetchAllConsultations(application = context)
            }
            override fun onResponse(call: Call<Consultation>, response: Response<Consultation>) {
                if (response.isSuccessful) {
                    consultation.isSend = true
                    insertConsultationOffline(context as Application,consultation)
                    Toast.makeText(context, "send Consultation was Successfully", Toast.LENGTH_SHORT)
                        .show()
                    fetchAllConsultations(application = context)
                } else {
                    consultation.isSend = false
                    insertConsultationOffline(context as Application,consultation)
                    Toast.makeText(context, "insert Locally", Toast.LENGTH_SHORT).show()
                    fetchAllConsultations(application = context)

                }
            }

        })
    }
}