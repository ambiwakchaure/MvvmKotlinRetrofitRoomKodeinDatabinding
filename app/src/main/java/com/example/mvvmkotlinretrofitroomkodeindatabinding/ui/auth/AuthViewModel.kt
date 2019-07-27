package com.example.mvvmkotlinretrofitroomkodeindatabinding.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.repositories.UserRepository
import com.example.mvvmkotlinretrofitroomkodeindatabinding.util.ApiException
import com.example.mvvmkotlinretrofitroomkodeindatabinding.util.Coroutines
import com.example.mvvmkotlinretrofitroomkodeindatabinding.util.NoInternetException

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var confirmPassword: String? = null
    //for callbacks for view we need listner
    var authListner: AuthListner? = null

    //get user live data
    fun getLoggedInUser() = repository.getUser()


    fun onSignup(view : View){

        Intent(view.context,SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
    fun onSignin(view : View){

        Intent(view.context,LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

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
            }catch (e: NoInternetException) {
                authListner?.onFailure(e.message!!)
            }


        }


    }

    //handle signup button clicked
    fun onSignupButtonClicked(view: View) {

        authListner?.onStarted()

        if (name.isNullOrEmpty()) {
            authListner?.onFailure("Name is required")
            return
        }
        if (email.isNullOrEmpty()) {
            authListner?.onFailure("Email is required")
            return
        }
        if (password.isNullOrEmpty()) {
            authListner?.onFailure("Password is required")
            return
        }
        if (password != confirmPassword) {
            authListner?.onFailure("Password and confirm password should be same")
            return
        }



        Coroutines.main {
            try {
                val authResponse = repository.userSignup(name!!, email!!, password!!)
                authResponse.user?.let {
                    authListner?.onSuccess(it)
                    //save user into local db
                    repository.saveUser(it)
                    return@main
                }
                authListner?.onFailure(authResponse.message!!)
            } catch (e: ApiException) {
                authListner?.onFailure(e.message!!)
            }catch (e: NoInternetException) {
                authListner?.onFailure(e.message!!)
            }


        }


    }
}