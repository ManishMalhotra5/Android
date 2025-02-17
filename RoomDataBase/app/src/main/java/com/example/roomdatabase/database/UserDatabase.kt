package com.example.roomdatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabase.models.UserModel

@Database(entities = [UserModel::class], version = 1, exportSchema = true)
abstract  class UserDatabase : RoomDatabase(){
  abstract fun userDao (): UserDao

  companion object {
    private var userDatabase : UserDatabase? = null
    fun getInstance(context : Context)  : UserDatabase {
      if(userDatabase == null){
        userDatabase = Room.databaseBuilder(context,
          UserDatabase::class.java,
          "UserDatabse")
          .allowMainThreadQueries()
          .build()
      }
     return  userDatabase!!
    }

  }
}