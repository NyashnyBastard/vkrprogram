package com.example.test1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth



class MenuActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val mBtn = findViewById<ImageButton>(R.id.messageBtn)


        mBtn.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND)
            intent.data = Uri.parse("Email")
            val str_array = arrayOf("")
            intent.putExtra(Intent.EXTRA_EMAIL, str_array)
            intent.putExtra(Intent.EXTRA_SUBJECT, "")
            intent.putExtra(Intent.EXTRA_TEXT, "")
            intent.type = "message/rfc822"
            val a = Intent.createChooser(intent, "Перейти в мессенджер")
            startActivity(a)
        }


        }



        fun callRequest(view: View) {
            val callRequestIntent = Intent(this, MenuRequestActivity::class.java)
            startActivity(callRequestIntent)
        }

        fun chekList(view: View) {
            val chekListIntent = Intent(this, cheklistActivity::class.java)
            startActivity(chekListIntent)
        }

        fun Spis(view: View) {
            val SpisIntent = Intent(this, spisanieActivity::class.java)
            startActivity(SpisIntent)
        }

        fun getOff(view: View) {
            FirebaseAuth.getInstance().signOut()
            finish()

        }

    }
