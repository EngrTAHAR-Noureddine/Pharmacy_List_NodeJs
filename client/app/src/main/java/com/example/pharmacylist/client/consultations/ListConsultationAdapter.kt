package com.example.pharmacylist.client.consultations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmacylist.R
import com.example.pharmacylist.model.Consultation

class ListConsultationAdapter(val context: FragmentActivity?, var data:List<Consultation>):
    RecyclerView.Adapter<MyViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.consultation_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameUserConsulter.text = data[position].nameUser
        holder.locationUserConsulter.text = data[position].locationUser
        holder.namePharmacyBeConsulter.text = data[position].namePharmacy
        holder.locationPharmacyBeConsulter.text = data[position].locationPharmacy
        holder.theConsultation.text = data[position].consultation
    }

    override fun getItemCount()= data.size
}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val item = view.findViewById<View>(R.id.consultingItem) as View
    val nameUserConsulter = view.findViewById<TextView>(R.id.nameUserConsulter) as TextView
    val locationUserConsulter = view.findViewById<TextView>(R.id.locationUserConsulter) as TextView
    val namePharmacyBeConsulter = view.findViewById<TextView>(R.id.namePharmacyBeConsulter) as TextView
    val locationPharmacyBeConsulter = view.findViewById<TextView>(R.id.locationPharmacyBeConsulter) as TextView
    val theConsultation = view.findViewById<TextView>(R.id.theConsultation) as TextView
}