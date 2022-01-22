package com.example.pharmacylist.login.main_login

import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pharmacylist.databinding.LoginFragmentBinding

class LogInViewModelFactory(private val binding: LoginFragmentBinding
                            ,private val context : FragmentActivity
                            ,private val application:Application):ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel( binding ,context ,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}