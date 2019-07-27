package com.example.mvvmkotlinretrofitroomkodeindatabinding.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmkotlinretrofitroomkodeindatabinding.R
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.db.entities.User
import com.example.mvvmkotlinretrofitroomkodeindatabinding.databinding.ActivitySignupBinding
import com.example.mvvmkotlinretrofitroomkodeindatabinding.ui.home.HomeActivity
import com.example.mvvmkotlinretrofitroomkodeindatabinding.util.hide
import com.example.mvvmkotlinretrofitroomkodeindatabinding.util.show
import com.example.mvvmkotlinretrofitroomkodeindatabinding.util.snackbar
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.progress_bar
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(), AuthListner, KodeinAware {


    //create instance using codein framework
    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //bind view model to activity
        val binding: ActivitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        //get view model from ViewModelProviders
        val viewmodel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        //now we will set this view model
        binding.viewmodel = viewmodel
        //now define authlistner to view model
        viewmodel.authListner = this
        //check user logged in or not
        viewmodel.getLoggedInUser().observe(this, Observer { user ->

            if (user != null) {

                Intent(this, HomeActivity::class.java).also {
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
       // root_layout.snackbar("${user.name} is Logged In")
        //toast("${user.name} is Logged In")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
    }
}
