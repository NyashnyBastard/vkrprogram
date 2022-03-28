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
        toProductIntent.putExtra("Table","Products")
        toProductIntent.putExtra("Warehouse","Warehouse")
        startActivity(toProductIntent)
        finish()
    }

    fun toDrink(view: View) {
        val toProductIntent = Intent(this, RequestProductActivity::class.java)
        toProductIntent.putExtra("Table","Drinks")
        toProductIntent.putExtra("Warehouse","DrinksStock")
        startActivity(toProductIntent)
        finish()
    }

    fun toStorehouse(view:View){
        val toProductIntent = Intent(this, RequestProductActivity::class.java)
        toProductIntent.putExtra("Table","Storehouse")
        toProductIntent.putExtra("Warehouse","Storehouse")
        startActivity(toProductIntent)
        finish()
    }
    fun toDetergents(view: View){
        val toProductIntent = Intent(this, RequestProductActivity::class.java)
        toProductIntent.putExtra("Table","Detergents")
        toProductIntent.putExtra("Warehouse","Detergents")
        startActivity(toProductIntent)
        finish()

    }

}