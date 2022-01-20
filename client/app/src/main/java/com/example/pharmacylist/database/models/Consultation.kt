package com.example.pharmacylist.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(tableName = "consultation_table")
data class Consultation (
    @ColumnInfo(name = "nameUser")
    var nameUser: String = "",

    @ColumnInfo(name = "locationUser")
    var locationUser: String = "",

    @ColumnInfo(name = "namePharmacy")
    var namePharmacy: String = "",

    @ColumnInfo(name = "locationPharmacy")
    var locationPharmacy: String = "",

    @ColumnInfo(name = "consultation")
    var consultation: String = "",

    @ColumnInfo(name = "isSend")
    var isSend: Boolean = true,

)