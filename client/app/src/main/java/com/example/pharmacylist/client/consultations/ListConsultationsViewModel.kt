package com.example.pharmacylist.client.consultations

import android.annotation.SuppressLint
import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pharmacylist.controller.ConsultationController.allConsultations
import com.example.pharmacylist.controller.ConsultationController.fetchAllConsultations
import com.example.pharmacylist.databinding.ListConsultationsFragmentBinding
import com.example.pharmacylist.model.Consultation


class ListConsultationsViewModel(private val binding: ListConsultationsFragmentBinding,
                                 @SuppressLint("StaticFieldLeak") private val context : FragmentActivity,
                                 application : Application)
    : ViewModel() {

    private var _consultations  = allConsultations
    val consultations: LiveData<List<Consultation>?>
        get() = _consultations

    init {
        fetchAllConsultations(application)
    }
}