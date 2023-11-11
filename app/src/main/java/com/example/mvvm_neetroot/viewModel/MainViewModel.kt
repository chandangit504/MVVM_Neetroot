package com.example.mvvm_neetroot.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mvvm_neetroot.Repository.Repository
import com.example.mvvm_neetroot.Roomcomponent.Dao
import com.example.mvvm_neetroot.Roomcomponent.MainDb
import com.example.mvvm_neetroot.table.Contact

class MainViewModel(application : Application) : AndroidViewModel(application){

    lateinit var dao: Dao
    lateinit var repo: Repository
    lateinit var readContact: LiveData<List<Contact>>
    init {
        dao = MainDb.createdb(application).dao()
        repo= Repository(dao)
        readContact =repo.readData()
    }
        fun delete(contact: Contact) {
        dao.DeleeteData(contact)
    }

}