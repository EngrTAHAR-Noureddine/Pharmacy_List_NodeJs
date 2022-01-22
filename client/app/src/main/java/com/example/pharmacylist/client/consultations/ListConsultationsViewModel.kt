package com.example.pharmacylist.client.consultations

import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pharmacylist.Service.RetrofitServices
import com.example.pharmacylist.controller.ConsultationController.allConsultations
import com.example.pharmacylist.controller.ConsultationController.fetchAllConsultations
import com.example.pharmacylist.databinding.ListConsultationsFragmentBinding
import com.example.pharmacylist.model.Consultation
import retrofit2.Call
import retrofit2.Response


class ListConsultationsViewModel(private val binding: ListConsultationsFragmentBinding,
                                 private val context : FragmentActivity,
                                 private val application : Application)
    : ViewModel() {

    private var _consultations  = allConsultations
    val consultations: LiveData<List<Consultation>?>
        get() = _consultations

    init {
        fetchAllConsultations(application)
    }
}