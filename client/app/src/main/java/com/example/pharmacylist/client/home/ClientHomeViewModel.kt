package com.example.pharmacylist.client.home

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pharmacylist.Service.RetrofitServices
import com.example.pharmacylist.databinding.ClientHomeFragmentBinding
import com.example.pharmacylist.model.User
import retrofit2.Call
import retrofit2.Response

class ClientHomeViewModel( private val binding: ClientHomeFragmentBinding
                          , private val context : FragmentActivity) : ViewModel() {

    private var _pharmacies  = MutableLiveData<List<User>?>()
    val pharmacies: LiveData<List<User>?>
        get() = _pharmacies

    private fun fetchAllUsers():MutableLiveData<List<User>?>{
        val data = MutableLiveData<List<User>?>()

        val call = RetrofitServices.endpoint.fetchAllUsers()

        call.enqueue(object : retrofit2.Callback<List<User>>{


            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {

                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    data.value = res
                }else{
                    data.value = null
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }

    init {
        _pharmacies = fetchAllUsers()
    }





}