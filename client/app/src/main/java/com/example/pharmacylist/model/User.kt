package com.example.pharmacylist.model

import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("nameUser") var nameUser :String? = null,
    @SerializedName("locationUser") var locationUser :String? = null,
    @SerializedName("photoUser") var photoUser :String? = null,
    @SerializedName("typeUser") var typeUser :String? = null,
    @SerializedName("passwordUser") var passwordUser : String? = null,
    @SerializedName("emailUser") var emailUser : String? = null,
    @SerializedName("phoneUser") var phoneUser :String? = null
    )