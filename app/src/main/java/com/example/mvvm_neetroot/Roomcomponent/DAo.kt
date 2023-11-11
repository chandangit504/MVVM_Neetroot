package com.example.mvvm_neetroot.Roomcomponent

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mvvm_neetroot.table.Contact


// Dao we have created for give the dat or delete data
@Dao
interface Dao {

    @Query("SELECT * FROM Contact_table")
    fun readData(): LiveData<List<Contact>>

    @Update
    fun updateData(contact: Contact)

    @Delete
    fun DeleeteData(contact: Contact)


    //  avoid comfits by onconflits
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertData(contact: Contact)
}
