package com.example.mvvm_neetroot.Roomcomponent

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm_neetroot.table.Contact


@Database(entities = arrayOf(Contact::class), version = 3, exportSchema = false)
abstract class MainDb : RoomDatabase() {
    abstract  fun  dao() : Dao


    companion object{
         var  db : MainDb?=null
         fun createdb(context: Context): MainDb {
            if (db == null){
                // here why we use .allowMainThreadQueries()   fallbackToDestructiveMigration()
                db = Room.databaseBuilder(context, MainDb::class.java, "my_db" ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
            }
            return db!!
        }

    }


}