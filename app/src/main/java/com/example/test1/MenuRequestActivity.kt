package com.example.test1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View

class MenuRequestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_request)
    }



    fun getBack(view: View) {
        val getBackIntent = Intent(this, MenuActivity::class.java)
        startActivity(getBackIntent)
        finish()
    }

    fun toProduct(view: View){
        val toProductIntent = Intent(this, RequestProductActivity::class.java)
        startActivity(toProductIntent)
        finish()
    }

    fun toDrink(view: View) {
        val toDrinkIntent = Intent(this, RequestProduct1Activity::class.java)
        startActivity(toDrinkIntent)
        finish()
    }

    fun toStorehouse(view:View){
        val toStorehouseIntent = Intent(this,RequestProduct2Activity::class.java)
        startActivity(toStorehouseIntent)
        finish()
    }
    fun toDetergents(view: View){
        val toDetergentsIntent = Intent(this, RequestProduct3Activity::class.java)
        startActivity(toDetergentsIntent)
        finish()

    }

}