package com.example.pharmacylist.client.consultations

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pharmacylist.databinding.ListConsultationsFragmentBinding

class ListConsultationViewModelFactory(private val binding: ListConsultationsFragmentBinding
                                       ,private val context : FragmentActivity
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListConsultationsViewModel::class.java)) {
            return ListConsultationsViewModel(binding ,context ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}