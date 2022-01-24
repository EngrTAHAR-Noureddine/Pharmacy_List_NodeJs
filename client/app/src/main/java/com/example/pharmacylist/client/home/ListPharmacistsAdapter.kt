package com.example.pharmacylist.client.home

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmacylist.R
import com.example.pharmacylist.model.User
import com.google.gson.Gson

class ListPharmacistsAdapter(val context: FragmentActivity?, private var data:List<User>?):
    RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.client_item_pharmacist, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(data!=null){
        holder.namePharmacy.text = data!![position].nameUser
        holder.locationPharmacy.text = data!![position].locationUser

        holder.item.setOnClickListener {

            val pharmacy = User(
                nameUser = data!![position].nameUser,
                locationUser = data!![position].locationUser,
                passwordUser = data!![position].passwordUser,
                emailUser = data!![position].emailUser,
                phoneUser = data!![position].phoneUser,
                photoUser = data!![position].photoUser,
                typeUser = data!![position].typeUser
            )

            val gson = Gson()
            val prefUser = context?.getSharedPreferences("PharmacyProfile", Context.MODE_PRIVATE)
            val editorUser : SharedPreferences.Editor? = prefUser?.edit()
            val json = gson.toJson(pharmacy)
            editorUser?.apply{
                putString("pharmacyProfile",json)
            }?.apply()

            val text = "you clicked on item $json"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()

            context?.findNavController(R.id.myNavHostFragment)?.navigate(R.id.to_Client_Pharmacy_Detail)

        }
        }
    }

    override fun getItemCount() = if(data!=null )data!!.size else 0
}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val item = view.findViewById(R.id.itemPharmacistClientHome) as View
    val namePharmacy = view.findViewById(R.id.Pharmacy_Name) as TextView
    val locationPharmacy = view.findViewById(R.id.Pharmacy_Location) as TextView
}