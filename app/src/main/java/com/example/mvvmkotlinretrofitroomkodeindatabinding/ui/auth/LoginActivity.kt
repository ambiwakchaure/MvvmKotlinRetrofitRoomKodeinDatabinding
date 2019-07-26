package com.example.mvvmkotlinretrofitroomkodeindatabinding.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmkotlinretrofitroomkodeindatabinding.R
import com.example.mvvmkotlinretrofitroomkodeindatabinding.databinding.ActivityLoginBinding
import com.example.mvvmkotlinretrofitroomkodeindatabinding.util.hide
import com.example.mvvmkotlinretrofitroomkodeindatabinding.util.show
import com.example.mvvmkotlinretrofitroomkodeindatabinding.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),AuthListner {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //bind view model to activity
        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        //get view model from ViewModelProviders
        val viewmodel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        //now we will set this view model
        binding.viewmodel = viewmodel
        //now define authlistner to view model
        viewmodel.authListner = this
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        //observe the login response
        loginResponse.observe(this, Observer {
            progress_bar.hide()
            toast(it)
        })
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        toast(message)
    }
}
