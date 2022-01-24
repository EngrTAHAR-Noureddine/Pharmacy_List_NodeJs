package com.example.pharmacylist.client.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pharmacylist.R
import com.example.pharmacylist.databinding.ClientHomeFragmentBinding


class ClientHomeFragment : Fragment() {

    private lateinit var binding: ClientHomeFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.client_home_fragment,container,false)
        val application = requireNotNull(this.activity).application

        val viewModelFactory = ClientHomeViewModelFactory(binding ,this.requireActivity(),application)

        val clientHomeViewModel = ViewModelProvider(this, viewModelFactory)[ClientHomeViewModel::class.java]

        binding.clientHomeViewModel = clientHomeViewModel
        binding.lifecycleOwner = this

        this.binding.recycleViewPharmacies.layoutManager = LinearLayoutManager(activity)


        clientHomeViewModel.pharmacies.observe(
            viewLifecycleOwner,  {
                this.binding.recycleViewPharmacies.adapter = ListPharmacistsAdapter(activity,it)
            }
        )

        return binding.root
    }



}