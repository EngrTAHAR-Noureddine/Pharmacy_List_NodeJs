package com.example.pharmacylist.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "user_table")
data class User(
    @ColumnInfo(name = "nameUser")
    var nameUser: String = "",

    @ColumnInfo(name = "emailUser")
    var emailUser: String = "",

    @ColumnInfo(name = "passwordUser")
    var passwordUser: String = "",

    @ColumnInfo(name = "locationUser")
    var locationUser: String = "",

    @ColumnInfo(name = "photoUser")
    var photoUser: String = "",

    @ColumnInfo(name = "typeUser")
    var typeUser: String = "",

    @ColumnInfo(name = "phoneUser")
    var phoneUser: String = ""
)