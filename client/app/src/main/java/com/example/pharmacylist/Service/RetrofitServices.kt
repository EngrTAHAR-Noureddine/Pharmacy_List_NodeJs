package com.example.pharmacylist.Service

import com.example.pharmacylist.EndPoints.EndPoints
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServices {
    val endpoint: EndPoints by lazy {
        var baseUrl = "https://322f-41-102-155-78.ngrok.io/"
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build().create(EndPoints::class.java)
    }
}