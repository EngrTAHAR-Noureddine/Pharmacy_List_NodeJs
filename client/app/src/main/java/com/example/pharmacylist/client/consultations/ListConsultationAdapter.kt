package com.example.pharmacylist.client.consultations

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmacylist.R
import com.example.pharmacylist.model.Consultation

class ListConsultationAdapter(val context: FragmentActivity?, private var data:List<Consultation>):
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
        holder.item.setBackgroundColor(if(!data[position].isSend) Color.rgb(254,2,5) else Color.DKGRAY)
    }

    override fun getItemCount()= data.size
}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val item = view.findViewById(R.id.consultingItem) as View
    val nameUserConsulter = view.findViewById(R.id.nameUserConsulter) as TextView
    val locationUserConsulter = view.findViewById(R.id.locationUserConsulter) as TextView
    val namePharmacyBeConsulter = view.findViewById(R.id.namePharmacyBeConsulter) as TextView
    val locationPharmacyBeConsulter = view.findViewById(R.id.locationPharmacyBeConsulter) as TextView
    val theConsultation = view.findViewById(R.id.theConsultation) as TextView
}