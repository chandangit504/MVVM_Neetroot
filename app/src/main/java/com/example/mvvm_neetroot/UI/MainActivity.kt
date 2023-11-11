package com.example.mvvm_neetroot.UI

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_neetroot.R
import com.example.mvvm_neetroot.adapter.Adapter
import com.example.mvvm_neetroot.databinding.ActivityMainBinding
import com.example.mvvm_neetroot.table.Contact
import com.example.mvvm_neetroot.viewModel.MainViewModel
import java.net.URI

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var datalist = ArrayList<Contact>()
    var adapter = Adapter(datalist, this)

    lateinit var mainViewmodel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mainViewmodel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewmodel.readContact.observeForever {
            datalist.clear()
            datalist.addAll(it)
            adapter.notifyDataSetChanged()
        }

        binding.addcontact.setOnClickListener {
            startActivity(Intent(this, AddUpdateActivity::class.java))
        }

        val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }


                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    if (direction == ItemTouchHelper.RIGHT) {
                        var contact: Contact = datalist.get(viewHolder.adapterPosition)
                        var number = contact.number

                        val phone_intent = Intent(Intent.ACTION_CALL)
                        phone_intent.data = Uri.parse("tel:$number")
                        startActivity(phone_intent)
                        adapter.notifyDataSetChanged()
                    } else {
                        var contact: Contact = datalist.get(viewHolder.adapterPosition)
                        mainViewmodel.delete(contact = contact)
                        datalist.removeAt(viewHolder.adapterPosition)
                        adapter.notifyItemRemoved(viewHolder.adapterPosition)

                    }
                }

            }

        val itemtouchhelper = ItemTouchHelper(itemTouchHelperCallback)
        itemtouchhelper.attachToRecyclerView(binding.recyclerView)


        // passing adapter value to the resiclerview id

        binding.recyclerView.adapter = adapter

        //how to show on sreen
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
    }
}