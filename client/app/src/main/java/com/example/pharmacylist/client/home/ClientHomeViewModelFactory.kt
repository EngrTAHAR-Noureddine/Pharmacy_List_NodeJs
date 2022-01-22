package com.example.pharmacylist.client.home

import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pharmacylist.databinding.ClientHomeFragmentBinding

class ClientHomeViewModelFactory (private val binding: ClientHomeFragmentBinding
                                  ,private val context : FragmentActivity,
                                  private val application : Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClientHomeViewModel::class.java)) {
            return ClientHomeViewModel(binding ,context ,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}