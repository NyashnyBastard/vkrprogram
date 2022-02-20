package com.example.test1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class cheklistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheklist)
    }
    fun getBackTo(view: View)
    {
        val getBackToIntent = Intent(this,MenuActivity::class.java)
        startActivity(getBackToIntent)
        finish()
    }
}