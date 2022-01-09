package com.example.pharmacylist.login.main_login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pharmacylist.Service.RetrofitServices
import com.example.pharmacylist.client.ClientActivity
import com.example.pharmacylist.databinding.LoginFragmentBinding
import com.example.pharmacylist.model.User
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response


class LoginViewModel(private val binding: LoginFragmentBinding,
                     private val context : FragmentActivity) : ViewModel() {


    private var _email = MutableLiveData<String>()
    private var _password  = MutableLiveData<String>()
    private var _users  = MutableLiveData<List<User>?>()


    private var pref : SharedPreferences? = null

    val email: LiveData<String>
        get() = _email
    val password: LiveData<String>
        get() = _password
    val users: LiveData<List<User>?>
        get() = _users


    private fun fetchAllUsers():MutableLiveData<List<User>?>{
        val data = MutableLiveData<List<User>?>()

        val call = RetrofitServices.endpoint.fetchAllUsers()

        call.enqueue(object : retrofit2.Callback<List<User>>{


            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {

                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    data.value = res
                }else{
                    data.value = null
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }

    init {
        _email.value = ""
        _password.value = ""
        _users = fetchAllUsers()
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
                    /*
                    to retrieve :
                                Gson gson = new Gson();
                                String json = mPrefs.getString("MyObject", "");
                                MyObject obj = gson.fromJson(json, MyObject.class);
                    * */

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


        }else{
            val text = "the _email and _password are null"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }

    }


}


