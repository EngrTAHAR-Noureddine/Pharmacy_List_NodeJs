package com.example.pharmacylist.EndPoints

import com.example.pharmacylist.model.Consultation
import com.example.pharmacylist.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EndPoints {

    @GET("api/user")
    fun fetchAllUsers(): Call<List<User>>

    @GET("api/consultation")
    fun fetchAllConsultations(): Call<List<Consultation>>

    @POST("api/consultation")
    fun setConsultation(@Body login: Consultation): Call<Consultation>

}