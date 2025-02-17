package com.example.roomdatabase.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roomdatabase.models.UserModel
import com.example.roomdatabase.recyclerviewadapter.RecyclerViewAdapter

@Dao
interface UserDao {
    @Insert
    fun insertData(userMode:UserModel)

    @Update
    fun updateData(userMode: UserModel)

    @Query("SELECT * FROM UserModel")
    fun getList() : List<UserModel>
}