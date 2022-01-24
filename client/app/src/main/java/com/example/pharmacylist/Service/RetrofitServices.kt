package com.example.pharmacylist.Service

import com.example.pharmacylist.EndPoints.EndPoints
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServices {
    val endpoint: EndPoints by lazy {
        var baseUrl = "https://cced-41-102-189-183.ngrok.io/"
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build().create(EndPoints::class.java)
    }
}