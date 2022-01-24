package com.example.pharmacylist.controller

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import com.example.pharmacylist.Service.RetrofitServices
import com.example.pharmacylist.client.ClientActivity
import com.example.pharmacylist.controller.LocalData.fetchUsers
import com.example.pharmacylist.controller.LocalData.insertUsers
import com.example.pharmacylist.model.User
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

object UserController {

    var allUsers = MutableLiveData<List<User>?>()

     fun fetchAllUsers(application: Application){
        val call = RetrofitServices.endpoint.fetchAllUsers()


        call.enqueue(object : retrofit2.Callback<List<User>>{
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    allUsers.value = res
                    insertUsers(application,res)

                }else{
                    allUsers.value = fetchUsers(application)

                }

            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                allUsers.value = fetchUsers(application)
            }
        })
    }

    fun login(user:User,context: FragmentActivity){

        val call = RetrofitServices.endpoint.loginUser(user)

        call.enqueue(object : retrofit2.Callback<User?>{

            override fun onResponse(call: Call<User?>, response: Response<User?>) {

                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    val userFounded: User? = res
                    if(userFounded != null){
                        val pref = context.getSharedPreferences("TypeUserFile", Context.MODE_PRIVATE)
                        val editor : SharedPreferences.Editor = pref.edit()
                        editor.apply{
                            putString("typeUser",userFounded.typeUser)
                        }.apply()

                        val gson = Gson()
                        val prefUser = context.getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
                        val editorUser : SharedPreferences.Editor = prefUser.edit()
                        val json = gson.toJson(userFounded)
                        editorUser.apply{
                            putString("userProfile",json)
                        }.apply()

                        val intent = Intent(context, ClientActivity::class.java)
                        context.startActivity(intent)
                        context.finish()

                    }else{
                        val text = "Users find but email and password false"
                        val duration = Toast.LENGTH_SHORT
                        val toast = Toast.makeText(context, text, duration)
                        toast.show()
                    }

                }else{
                    val text = "Login Failed"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(context, text, duration)
                    toast.show()
                }
            }

            override fun onFailure(call: Call<User?>, t: Throwable) {

                val text = "Login Error"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(context, text, duration)
                toast.show()
            }
        })



    }
}