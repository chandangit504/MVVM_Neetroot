package com.example.mvvm_neetroot.Repository

import androidx.lifecycle.LiveData
import com.example.mvvm_neetroot.Roomcomponent.Dao
import com.example.mvvm_neetroot.table.Contact


//this is basically to get the data from dao a
class Repository(val dao: Dao) {
    
    fun readData(): LiveData<List<Contact>> = dao.readData()

    fun updateData(contact: Contact) = dao.updateData(contact)

    fun DeleeteData(contact: Contact) = dao.DeleeteData(contact)

    fun insertData(contact: Contact) = dao.insertData(contact)

}