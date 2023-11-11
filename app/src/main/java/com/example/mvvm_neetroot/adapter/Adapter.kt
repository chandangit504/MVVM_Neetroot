package com.example.mvvm_neetroot.adapter

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_neetroot.UI.AddUpdateActivity
import com.example.mvvm_neetroot.databinding.ContactitemBinding
import com.example.mvvm_neetroot.table.Contact

class Adapter(var data: ArrayList<Contact>, var context : Context) : RecyclerView.Adapter<Adapter.ViewHolder>(){

    inner class  ViewHolder(val binding : ContactitemBinding):  RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ContactitemBinding.inflate(LayoutInflater.from(context), parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
      return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         var contactlist: Contact  = data.get(position)
//        data.get(position).id?.let { holder.binding.imageView.setImageResource(it) }
        holder.binding.name.text= data.get(position).name.toString()
        holder.binding.number.text= data.get(position).number.toString()

        if (contactlist.profileimage!= null){
            var bitmap= BitmapFactory.decodeByteArray(contactlist.profileimage,0 ,contactlist.profileimage!!.size )
            holder.binding.imageView.setImageBitmap(bitmap)
        }
        holder.itemView.setOnClickListener{
            var intent= Intent(context, AddUpdateActivity::class.java).putExtra("MODE", 2).putExtra("old",contactlist)
            context.startActivity(intent)
        }
    }
}