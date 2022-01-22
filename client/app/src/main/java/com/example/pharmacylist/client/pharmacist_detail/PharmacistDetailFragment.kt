package com.example.pharmacylist.client.pharmacist_detail

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.pharmacylist.R
import com.example.pharmacylist.databinding.PharmacistDetailFragmentBinding
import com.example.pharmacylist.model.User
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class PharmacistDetailFragment : Fragment() {

    private lateinit var binding : PharmacistDetailFragmentBinding

    private fun getData(): String?{
        val prefUser = activity?.getSharedPreferences("PharmacyProfile", Context.MODE_PRIVATE)
        return prefUser?.getString("pharmacyProfile","")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = DataBindingUtil.inflate(
            inflater,
            R.layout.pharmacist_detail_fragment,
            container,
            false
        )


        val gson = Gson()
        val json :String = if(getData()!=null) getData()!! else ""
        val p :User? = gson.fromJson(json, User::class.java)
        val application = requireNotNull(this.activity).application

        val viewModelFactory = PharmacyDetailFragmentFactory(p,binding ,this.requireActivity(),application)



        val pharmacyDetailViewModel = ViewModelProvider(this, viewModelFactory)[PharmacistDetailViewModel::class.java]
        binding.pharmacyViewModel = pharmacyDetailViewModel

        binding.lifecycleOwner = this

        binding.adresseUser.text = if(p!=null && p.locationUser != null ) p.locationUser.toString() else "--------"

        binding.nameUser.text = if(p!=null && p.nameUser != null ) p.nameUser.toString() else "--------"

        if(p!=null && p.photoUser !=null) Picasso.with(context).load(p.photoUser).fit().centerInside().into(binding.photoUser);

        binding.buttonPhone.setOnClickListener {
            val user = p as User?
            if(user != null){
                val tel = user.phoneUser
                val url = Uri.parse("tel:$tel")
                val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(url.toString()))
                try {
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(
                        activity,
                        "You haven't phone application to make a call",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.buttonMap.setOnClickListener {
            val user = p as User?
            if(user != null){
                val latitude = 28.0339
                val longitude = 1.6596
                val url = Uri.parse("geo:$latitude,$longitude")
                val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(url.toString()))
                try {
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(
                        activity,
                        "You haven't an application to find a location",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }





        return binding.root
    }


}