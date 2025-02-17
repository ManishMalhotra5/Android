package com.example.loginsysfragments.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class RecyclerModel(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,

    @ColumnInfo("Name")
    var name : String?=null,

    @ColumnInfo("rollNo.")
    var rollno : Int?= 0
)