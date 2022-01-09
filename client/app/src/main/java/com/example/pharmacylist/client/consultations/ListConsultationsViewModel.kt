package com.example.pharmacylist.client.consultations

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pharmacylist.Service.RetrofitServices
import com.example.pharmacylist.databinding.ListConsultationsFragmentBinding
import com.example.pharmacylist.model.Consultation
import retrofit2.Call
import retrofit2.Response


class ListConsultationsViewModel(private val binding: ListConsultationsFragmentBinding,private val context : FragmentActivity)
    : ViewModel() {

    private var _consultations  = MutableLiveData<List<Consultation>?>()
    val consultations: LiveData<List<Consultation>?>
        get() = _consultations

    private fun fetchAllConsultations():MutableLiveData<List<Consultation>?>{
        val data = MutableLiveData<List<Consultation>?>()

        val call = RetrofitServices.endpoint.fetchAllConsultations()

        call.enqueue(object : retrofit2.Callback<List<Consultation>>{


            override fun onResponse(
                call: Call<List<Consultation>>,
                response: Response<List<Consultation>>
            ) {

                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    data.value = res
                }else{
                    data.value = null
                }
            }

            override fun onFailure(call: Call<List<Consultation>>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }

    init {
        _consultations = fetchAllConsultations()
    }
}