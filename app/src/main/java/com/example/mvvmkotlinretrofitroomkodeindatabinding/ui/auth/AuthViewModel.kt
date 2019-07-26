package com.example.mvvmkotlinretrofitroomkodeindatabinding.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.repositories.UserRepository
import com.example.mvvmkotlinretrofitroomkodeindatabinding.util.ApiException
import com.example.mvvmkotlinretrofitroomkodeindatabinding.util.Coroutines

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    var email: String? = null
    var password: String? = null
    //for callbacks for view we need listner
    var authListner: AuthListner? = null

    //get user live data
    fun getLoggedInUser() = repository.getUser()

    //handle login button clicked
    fun onLoginButtonClicked(view: View) {
        authListner?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListner?.onFailure("Invalid email or password")
            return
        }

        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListner?.onSuccess(it)
                    //save user into local db
                    repository.saveUser(it)
                    return@main
                }
                authListner?.onFailure(authResponse.message!!)
            } catch (e: ApiException) {
                authListner?.onFailure(e.message!!)
            }

        }


    }
}