package com.example.pharmacylist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_table")
data class User(
    @SerializedName("id") @PrimaryKey(autoGenerate = true) var id:Long?=null,
    @SerializedName("nameUser") @ColumnInfo(name = "nameUser") var nameUser :String? = null,
    @SerializedName("locationUser") @ColumnInfo(name = "locationUser") var locationUser :String? = null,
    @SerializedName("photoUser") @ColumnInfo(name = "photoUser") var photoUser :String? = null,
    @SerializedName("typeUser") @ColumnInfo(name = "typeUser") var typeUser :String? = null,
    @SerializedName("passwordUser") @ColumnInfo(name = "passwordUser") var passwordUser : String? = null,
    @SerializedName("emailUser") @ColumnInfo(name = "emailUser") var emailUser : String? = null,
    @SerializedName("phoneUser") @ColumnInfo(name = "phoneUser") var phoneUser :String? = null
    )