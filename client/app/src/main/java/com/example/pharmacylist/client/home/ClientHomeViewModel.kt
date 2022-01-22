package com.example.pharmacylist.client.home

import android.app.Application
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pharmacylist.controller.UserController.allUsers
import com.example.pharmacylist.controller.UserController.fetchAllUsers
import com.example.pharmacylist.databinding.ClientHomeFragmentBinding
import com.example.pharmacylist.model.User

class ClientHomeViewModel( private val binding: ClientHomeFragmentBinding
                          , private val context : FragmentActivity,private val application : Application) : ViewModel() {

    var pharmacies :MutableLiveData<List<User>?> = allUsers


    init {
        fetchAllUsers(application)
    }







}