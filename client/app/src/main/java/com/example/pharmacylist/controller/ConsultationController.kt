package com.example.pharmacylist.controller

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.pharmacylist.Service.RetrofitServices
import com.example.pharmacylist.model.Consultation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ConsultationController {

    fun fetchAllConsultations(): MutableLiveData<List<Consultation>?> {
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
                }else{
                    data.value = null
                }
            }

            override fun onFailure(call: Call<List<Consultation>>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }


     fun consultationFun(consultation: Consultation, context:Context){

        val call = RetrofitServices.endpoint.setConsultation(consultation)

        call.enqueue(object : Callback<Consultation> {

            override fun onFailure(call: Call<Consultation>, t: Throwable) {
                Toast.makeText(context, "Error!", Toast.LENGTH_SHORT)
                    .show()
            }
            override fun onResponse(call: Call<Consultation>, response: Response<Consultation>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "send Consultation was Successfully", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(context, "send Consultation was Failed", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        })
    }
}