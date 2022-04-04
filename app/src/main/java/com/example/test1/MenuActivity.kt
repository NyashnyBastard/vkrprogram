package com.example.test1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.test1.chekList.CheckListActivity
import com.google.firebase.auth.FirebaseAuth

class MenuActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val Btn = findViewById<ImageButton>(R.id.messageBtn)

        Btn.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.data = Uri.parse("")
            val str_array = arrayOf("")
            intent.putExtra(Intent.EXTRA_EMAIL, str_array)
            intent.type = "message/rfc822"
            val a = Intent.createChooser(intent,"")
            startActivity(a)
        }


    }
    fun callRequest(view: View){
        val callRequestIntent = Intent(this, MenuRequestActivity::class.java)
        startActivity(callRequestIntent)
        finish()
    }
    fun chekList(view: View){
        val chekListIntent = Intent(this,CheckListActivity::class.java)
        startActivity(chekListIntent)
        finish()
    }
    fun Spis(view:View){
        val SpisIntent = Intent(this, NoteActivity::class.java)
        startActivity(SpisIntent)
        finish()
    }
    fun getOff(view:View){
        FirebaseAuth.getInstance().signOut()
        finish()
    }



}