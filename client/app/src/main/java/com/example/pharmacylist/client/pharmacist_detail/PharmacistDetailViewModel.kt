package com.example.pharmacylist.client.pharmacist_detail

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pharmacylist.controller.ConsultationController.consultationFun
import com.example.pharmacylist.databinding.PharmacistDetailFragmentBinding
import com.example.pharmacylist.login.LoginActivity
import com.example.pharmacylist.model.Consultation
import com.example.pharmacylist.model.User
import com.google.gson.Gson

class PharmacistDetailViewModel(private val pharmacy: User?, private val binding: PharmacistDetailFragmentBinding
                                , private val context : FragmentActivity,
                                private val application : Application
) : ViewModel() {

    private lateinit var user : User

    private var _consultation = MutableLiveData<String?>()


    private fun getData(): String?{
        val prefUser = context.getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        return prefUser.getString("userProfile","")
    }

    fun onSubmitConsultation(){

        val pref = context.getSharedPreferences("TypeUserFile", Context.MODE_PRIVATE)
        val typeUser = pref.getString("typeUser", null)
        if (typeUser!=null) {

            val gson = Gson()
            val json :String = if(getData()!=null) getData()!! else ""
            user = gson.fromJson(json, User::class.java)

            _consultation.value = if(binding.inputConsultation.text != null) binding.inputConsultation.text.toString() else null

            if(_consultation != null && _consultation.value != null){

                val consultation = Consultation(

                                                                nameUser = user.nameUser,
                                                                locationUser = user.locationUser,
                                                                namePharmacy = pharmacy?.nameUser,
                                                                locationPharmacy = pharmacy?.locationUser,
                                                                consultation = _consultation.value

                                                                )

                consultationFun(consultation,application)

            }

        } else {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
            context.finish()
        }

    }




}