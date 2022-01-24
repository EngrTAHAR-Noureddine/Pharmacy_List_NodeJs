package com.example.pharmacylist
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pharmacylist.client.ClientActivity


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pref = getSharedPreferences("TypeUserFile", Context.MODE_PRIVATE)
        val typeUser = pref.getString("typeUser", null)

        Log.d("typeUser", typeUser.toString())
            intent = Intent(applicationContext, ClientActivity::class.java)
            startActivity(intent)
            finish()

    }

}