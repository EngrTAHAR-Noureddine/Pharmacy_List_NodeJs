package com.example.pharmacylist.login.main_login

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pharmacylist.controller.UserController.allUsers
import com.example.pharmacylist.controller.UserController.fetchAllUsers
import com.example.pharmacylist.controller.UserController.login
import com.example.pharmacylist.databinding.LoginFragmentBinding
import com.example.pharmacylist.model.User


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


