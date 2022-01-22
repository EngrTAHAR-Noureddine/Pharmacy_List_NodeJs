package com.example.pharmacylist.client.pharmacist_detail

import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pharmacylist.databinding.PharmacistDetailFragmentBinding
import com.example.pharmacylist.model.User

class PharmacyDetailFragmentFactory(private val user:User?,private val binding: PharmacistDetailFragmentBinding
                                    ,private val context : FragmentActivity,
                                    private val application : Application
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PharmacistDetailViewModel::class.java)) {
            return PharmacistDetailViewModel(user, binding ,context ,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}