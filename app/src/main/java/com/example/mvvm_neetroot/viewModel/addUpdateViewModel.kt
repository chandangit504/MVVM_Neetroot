package com.example.mvvm_neetroot.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.mvvm_neetroot.Repository.Repository
import com.example.mvvm_neetroot.Roomcomponent.Dao
import com.example.mvvm_neetroot.Roomcomponent.MainDb
import com.example.mvvm_neetroot.table.Contact

class addUpdateViewModel(application: Application) : AndroidViewModel(application) {
    var dao: Dao
    var repo: Repository

    init {
          dao =   MainDb.createdb(application).dao()
          repo = Repository(dao)
    }

    fun insertData(contact: Contact, afterInsert:()-> Unit) {
         repo.insertData(contact)
         afterInsert()
    }
    fun updateData(contact: Contact, afterInsert:()-> Unit) {
         repo.updateData(contact)
         afterInsert()
    }

}