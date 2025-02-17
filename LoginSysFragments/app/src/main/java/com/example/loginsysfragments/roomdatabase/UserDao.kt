package com.example.loginsysfragments.roomdatabase

import com.example.loginsysfragments.adapter.RecyclerListViewAdapter
import com.example.loginsysfragments.models.RecyclerModel
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface UserDao {
    @Insert
    fun insertData(recylerModel: RecyclerModel)

    @Update
    fun updateData(recylerModel: RecyclerModel)

    @Query("SELECT * FROM recyclermodel")
    fun getList() : List<RecyclerListViewAdapter>
}