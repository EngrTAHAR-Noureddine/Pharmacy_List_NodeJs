package com.example.pharmacylist.client.consultations

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pharmacylist.R
import com.example.pharmacylist.databinding.ListConsultationsFragmentBinding
import com.example.pharmacylist.login.LoginActivity
import com.example.pharmacylist.model.Consultation
import com.example.pharmacylist.model.User
import com.google.gson.Gson


class ListConsultationsFragment : Fragment() {

    private lateinit var binding: ListConsultationsFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.list_consultations_fragment,container,false)
        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application

        val viewModelFactory = ListConsultationViewModelFactory(binding ,this.requireActivity(),application)

        val clientHomeViewModel = ViewModelProvider(this, viewModelFactory)[ListConsultationsViewModel::class.java]


        val pref = activity?.getSharedPreferences("TypeUserFile", Context.MODE_PRIVATE)
        val typeUser = pref?.getString("typeUser", null)

        Log.d("typeUser", typeUser.toString())

        if (typeUser!=null) {
            val gson = Gson()
            val json :String = if(getData()!=null) getData()!! else ""
            val user :User = gson.fromJson(json, User::class.java)

            this.binding.recycleView.layoutManager = LinearLayoutManager(activity)

            clientHomeViewModel.consultations.observe(
                viewLifecycleOwner,  {
                    it?.let{

                        val list = if(user!=null && user.nameUser!=null)
                            it.filter { consultation: Consultation -> consultation.nameUser==user.nameUser } else it

                        this.binding.recycleView.adapter = ListConsultationAdapter(activity,list)
                    }
                }
            )
        } else {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }







        return binding.root
    }

    private fun getData(): String?{
        val prefUser = activity?.getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        return prefUser?.getString("userProfile","")
    }

}