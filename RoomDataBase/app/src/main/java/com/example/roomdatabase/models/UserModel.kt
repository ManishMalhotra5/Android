package com.example.roomdatabase.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int  = 0,

    @ColumnInfo(name="Name")
    var name : String ? = null,

    @ColumnInfo(name = "rollNo")
    var rollNo: Int? = 0

)
