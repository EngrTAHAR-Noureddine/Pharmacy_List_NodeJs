package com.example.pharmacylist.client.home

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pharmacylist.controller.UserController.allUsers
import com.example.pharmacylist.controller.UserController.fetchAllUsers
import com.example.pharmacylist.databinding.ClientHomeFragmentBinding
import com.example.pharmacylist.model.User

class ClientHomeViewModel( private val binding: ClientHomeFragmentBinding
                          , private val context : FragmentActivity) : ViewModel() {

    private var _pharmacies  = allUsers
    val pharmacies: LiveData<List<User>?>
        get() = _pharmacies


    init {
        fetchAllUsers()
    }





}