package com.example.test1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class NoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
    }
    fun getBack11(view: View){
        val getBack11Intent = Intent(this, MenuActivity::class.java)
        startActivity(getBack11Intent)
        finish()
    }

}