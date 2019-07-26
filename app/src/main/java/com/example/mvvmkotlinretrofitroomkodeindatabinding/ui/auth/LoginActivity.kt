package com.example.mvvmkotlinretrofitroomkodeindatabinding.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmkotlinretrofitroomkodeindatabinding.R
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.db.AppDatabase
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.db.entities.User
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.network.MyApi
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.repositories.UserRepository
import com.example.mvvmkotlinretrofitroomkodeindatabinding.databinding.ActivityLoginBinding
import com.example.mvvmkotlinretrofitroomkodeindatabinding.ui.home.HomeActivity
import com.example.mvvmkotlinretrofitroomkodeindatabinding.util.hide
import com.example.mvvmkotlinretrofitroomkodeindatabinding.util.show
import com.example.mvvmkotlinretrofitroomkodeindatabinding.util.snackbar
import com.example.mvvmkotlinretrofitroomkodeindatabinding.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),AuthListner {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //create required instances first
        val api = MyApi()
        val db = AppDatabase(this)
        val repository = UserRepository(api,db)

        val factory = AuthViewModelFactory(repository)

        //bind view model to activity
        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        //get view model from ViewModelProviders
        val viewmodel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        //now we will set this view model
        binding.viewmodel = viewmodel
        //now define authlistner to view model
        viewmodel.authListner = this

        //check user logged in or not
        viewmodel.getLoggedInUser().observe(this, Observer {user ->

            if(user != null){

                Intent(this,HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
        root_layout.snackbar("${user.name} is Logged In")
        //toast("${user.name} is Logged In")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
    }
}
