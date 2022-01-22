package com.example.pharmacylist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "consultation_table")
data class Consultation(
    @SerializedName("id") @PrimaryKey(autoGenerate = true) var id:Long?=null,
    @SerializedName("nameUser") @ColumnInfo(name = "nameUser") var nameUser :String?,
    @SerializedName("locationUser") @ColumnInfo(name = "locationUser") var locationUser :String?,
    @SerializedName("namePharmacy") @ColumnInfo(name = "namePharmacy") var namePharmacy :String?,
    @SerializedName("locationPharmacy") @ColumnInfo(name = "locationPharmacy") var locationPharmacy :String?,
    @SerializedName("consultation") @ColumnInfo(name = "consultation")var consultation :String?,
    @SerializedName("isSend") @ColumnInfo(name = "isSend") var isSend: Boolean = true,
)
