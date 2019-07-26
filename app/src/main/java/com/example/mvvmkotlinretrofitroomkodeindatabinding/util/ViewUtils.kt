package com.example.mvvmkotlinretrofitroomkodeindatabinding.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

//to show toast
fun Context.toast(message : String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}
//snack bar
fun View.snackbar(message : String){
    Snackbar.make(this,message,Snackbar.LENGTH_LONG).also {snackbar ->
        snackbar.setAction("Ok"){
            snackbar.dismiss()
        }
    }.show()

}
fun ProgressBar.show(){
    visibility = View.VISIBLE
}
fun ProgressBar.hide(){
    visibility = View.GONE
}
