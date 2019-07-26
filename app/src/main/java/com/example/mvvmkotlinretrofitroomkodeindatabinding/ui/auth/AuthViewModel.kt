package com.example.mvvmkotlinretrofitroomkodeindatabinding.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.repositories.UserRepository

class AuthViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null
    //for callbacks for view we need listner
    var authListner: AuthListner? = null

    //handle login button clicked
    fun onLoginButtonClicked(view: View) {
        authListner?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListner?.onFailure("Invalid email or password")
            return
        }

        //get api reaponse from repository
        val loginResponse = UserRepository().userLogin(email!!,password!!)
        authListner?.onSuccess(loginResponse)

    }
}