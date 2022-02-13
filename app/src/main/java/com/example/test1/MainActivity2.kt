package com.example.test1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }


    fun getBack(view: View)
    {
        val getBackIntent = Intent(this, MainActivity::class.java)
        startActivity(getBackIntent)
        finishAffinity()
    }


}