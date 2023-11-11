package com.example.mvvm_neetroot.UI

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_neetroot.R
import com.example.mvvm_neetroot.databinding.ActivityAddUpdateBinding
import com.example.mvvm_neetroot.table.Contact
import com.example.mvvm_neetroot.viewModel.addUpdateViewModel
import com.github.dhaval2404.imagepicker.ImagePicker
import java.io.InputStream

class AddUpdateActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityAddUpdateBinding.inflate(layoutInflater)
    }


    var imagedata : ByteArray?= null
//    When we move from one app to other app , while retuning
//    You call registerForActivityResult on an Activity or Fragment instance.
//You pass a contract or an instance of ActivityResultContract as a parameter to specify the type of result you expect and how to handle it.
//The method returns an instance of ActivityResultLauncher, which you can use to launch the activity or fragment and handle the result.
    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!
                val inputstream: InputStream?= fileUri.let {
                     contentResolver.openInputStream(it)
                }
                imagedata = inputstream?.readBytes()
                binding.imageView2.setImageURI(fileUri)
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    private lateinit var ViewModel: addUpdateViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        ViewModel = ViewModelProvider(this).get(addUpdateViewModel::class.java)

        binding.imageView2.setOnClickListener {
            ImagePicker.with(this)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }

        }



        if (intent.hasExtra("MODE")) {
            if (intent.getIntExtra("MODE", -1) == 2) {
                binding.addBtn.text= "Update"
                var updtecontact: Contact
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    updtecontact = intent.getSerializableExtra("old", Contact::class.java)!!
                    binding.name.setText(updtecontact.name)
                    binding.number.setText(updtecontact.number)
                    imagedata = updtecontact.profileimage
                    if (imagedata!= null){
                        var bitmap= BitmapFactory.decodeByteArray(imagedata,0 ,imagedata!!.size )
                        binding.imageView2.setImageBitmap(bitmap)
                    }


                } else {
                    updtecontact = intent.getSerializableExtra("old") as Contact
                    binding.name.setText(updtecontact.name)
                    binding.number.setText(updtecontact.number)
                    imagedata = updtecontact.profileimage
                    if (imagedata!= null){
                        var bitmap= BitmapFactory.decodeByteArray(imagedata,0 ,imagedata!!.size )
                        binding.imageView2.setImageBitmap(bitmap)
                    }

                }
                binding.addBtn.setOnClickListener {
                    updtecontact.name = binding.name.text.toString()
                    updtecontact.number = binding.number.text.toString()
                    updtecontact.profileimage =imagedata
                    ViewModel.updateData(updtecontact) {
                        finish()
                    }
                }

            } else {
                binding.addBtn.setOnClickListener {
                    val contact: Contact = Contact(
                        name = binding.name.text.toString(),
                        number = binding.number.text.toString(),
                        profileimage = imagedata
                    )
                    ViewModel.insertData(contact) {
                        finish()
                    }
                }
            }
        } else {
            binding.addBtn.setOnClickListener {
                val contact: Contact = Contact(
                    name = binding.name.text.toString(),
                    number = binding.number.text.toString(),
                    profileimage = imagedata
                )
                ViewModel.insertData(contact) {
                    finish()
                }
            }
        }


    }
}