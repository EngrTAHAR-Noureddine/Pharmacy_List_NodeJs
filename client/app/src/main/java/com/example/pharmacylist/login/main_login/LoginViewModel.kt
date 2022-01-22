package com.example.pharmacylist.login.main_login

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pharmacylist.client.ClientActivity
import com.example.pharmacylist.controller.UserController.allUsers
import com.example.pharmacylist.controller.UserController.fetchAllUsers
import com.example.pharmacylist.controller.UserController.login
import com.example.pharmacylist.databinding.LoginFragmentBinding
import com.example.pharmacylist.model.User
import com.google.gson.Gson


class LoginViewModel(private val binding: LoginFragmentBinding,
                     private val context : FragmentActivity
                     ,private val application:Application) : ViewModel() {


    private var _email = MutableLiveData<String>()
    private var _password  = MutableLiveData<String>()
    private var _users  = allUsers
    private var _user = MutableLiveData<User?>()

    private var pref : SharedPreferences? = null

    val email: LiveData<String>
        get() = _email
    val password: LiveData<String>
        get() = _password
    val users: LiveData<List<User>?>
        get() = _users




    init {
        _email.value = ""
        _password.value = ""
        fetchAllUsers(application)
        this.pref = context?.getSharedPreferences("firstTime", Context.MODE_PRIVATE)
        if(getData() != null && getData()!! ){
            saveData(false)
        }
    }

    private fun saveData(firstTime:Boolean){
        val editor : SharedPreferences.Editor? = this.pref?.edit()
        editor?.apply{
            putBoolean("firstTime",firstTime)
        }?.apply()
    }
    private fun getData(): Boolean? {
        return this.pref?.getBoolean("firstTime",true)
    }



    fun onCLickLogIn(){

        _email.value = if(binding.inputEmail.text != null) binding.inputEmail.text.toString() else "none"
        _password.value = if(binding.inputPassword.text != null) binding.inputPassword.text.toString() else "none"

        if(binding.inputEmail.text != null && binding.inputPassword.text != null && _email.value!!.isNotEmpty() && _password.value!!.isNotEmpty()){

            var user = User()
            user.emailUser = _email.value
            user.passwordUser = _password.value

            login(user,context)


        }else{
            val text = "the _email and _password are null"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }

    }


}

/*
 var user : User? = null


            if(_users != null && _users.value != null){

                for (item in _users.value!!) {
                    if(item.emailUser == _email.value && item.passwordUser == _password.value){
                        user = item
                    }
                }

                if(user != null){
                    val pref = context.getSharedPreferences("TypeUserFile", Context.MODE_PRIVATE)
                    val editor : SharedPreferences.Editor = pref.edit()
                    editor.apply{
                        putString("typeUser",user.typeUser)
                    }.apply()

                    val gson = Gson()
                    val prefUser = context.getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
                    val editorUser : SharedPreferences.Editor = prefUser.edit()
                    val json = gson.toJson(user)
                    editorUser.apply{
                        putString("userProfile",json)
                    }.apply()

                        val intent = Intent(context, ClientActivity::class.java)
                        context.startActivity(intent)
                        context.finish()

                }else{
                    val text = "Users find but email and password false : ${_email.value} && ${_password.value}"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(context, text, duration)
                    toast.show()
                }

            }else{
                val text = "Users null "
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(context, text, duration)
                toast.show()
            }



* */


