package com.example.loginsysfragments.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.loginsysfragments.models.RecyclerModel


@Database(entities = [RecyclerModel::class], version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao() : UserDao

    companion object{
        private var notesDatabase : AppDatabase?= null

        fun getInstance(context: Context): AppDatabase{
            if(notesDatabase == null){
                notesDatabase = Room.databaseBuilder(context,
                    AppDatabase::class.java,
                    "AppDatabase")
                    .allowMainThreadQueries()
                    .build()
            }
            return notesDatabase!!
        }
    }

}