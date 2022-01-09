package com.example.pharmacylist.model

import com.google.gson.annotations.SerializedName


data class Consultation(
    @SerializedName("nameUser") var nameUser :String?,
    @SerializedName("locationUser") var locationUser :String?,
    @SerializedName("namePharmacy") var namePharmacy :String?,
    @SerializedName("locationPharmacy") var locationPharmacy :String?,
    @SerializedName("consultation") var consultation :String?
)
