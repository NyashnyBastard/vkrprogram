package com.example.test1

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import  androidx.fragment.app.DialogFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun callRequest(view: View)
    {
        val callRequestIntent = Intent(this, MainActivity2::class.java)
        startActivity(callRequestIntent)
    }



}