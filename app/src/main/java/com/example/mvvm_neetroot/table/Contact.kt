package com.example.mvvm_neetroot.table

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


// table is created for store one contact list

@Entity(tableName = "contact_table")
class Contact (
    @PrimaryKey(autoGenerate = true)  var id: Int?=null,
    var name: String,
    var number: String,
    var profileimage: ByteArray?=null

):Serializable