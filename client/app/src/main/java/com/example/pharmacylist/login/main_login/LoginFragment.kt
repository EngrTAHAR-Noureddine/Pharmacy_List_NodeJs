package com.example.pharmacylist.login.main_login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.pharmacylist.R
import com.example.pharmacylist.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.login_fragment,container,false)

        val viewModelFactory = LogInViewModelFactory(binding ,this.requireActivity())
        val logInViewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
        binding.loginViewModel = logInViewModel
        binding.lifecycleOwner = this


        logInViewModel.users.observe(
            viewLifecycleOwner,
            {
                it?.let {
                    val text = "users in fragment ${it.size}"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(context, text, duration)
                    toast.show()
                }
                if(it == null){
                    val text = "users null in login fragment"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(context, text, duration)
                    toast.show()
                }
            }
        )


        return binding.root

    }



}