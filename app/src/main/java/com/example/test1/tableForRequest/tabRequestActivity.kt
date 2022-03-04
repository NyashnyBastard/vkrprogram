package com.example.test1.tableForRequest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.test1.R
import com.example.test1.RequestActivity

class tabRequestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_request)
    }
    fun bClose(view: View){
        val bCloseIntent = Intent(this, RequestActivity::class.java)
        startActivity(bCloseIntent)
        finish()
    }
}