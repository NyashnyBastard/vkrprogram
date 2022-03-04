package com.example.test1

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.example.test1.MenuActivity as MenuActivity1

class MenuActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

    }
    fun callRequest(view: View)
    {
        val callRequestIntent = Intent(this, RequestActivity::class.java)
        startActivity(callRequestIntent)
    }
    fun chekList(view: View)
    {
        val chekListIntent = Intent(this,cheklistActivity::class.java)
        startActivity(chekListIntent)
    }
    fun Spis(view:View)
    {
        val SpisIntent = Intent(this, spisanieActivity::class.java)
        startActivity(SpisIntent)
    }
    fun getOff(view:View){
        FirebaseAuth.getInstance().signOut()
        finish()
    }



}