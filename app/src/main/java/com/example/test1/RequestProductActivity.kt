package com.example.test1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.API.AirTableClient
import com.example.test1.databinding.ActivityRequestProductBinding
import com.example.test1.models.Product
import com.example.test1.rc.ProductsAdapter


class RequestProductActivity : AppCompatActivity() {
    public lateinit var binding: ActivityRequestProductBinding
    lateinit var adapter: ProductsAdapter
    lateinit var listProduct: List<Product>
    private lateinit var namePos: String
    private lateinit var table: String
    private lateinit var warehouse: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_product)

        var prefs = this.getSharedPreferences(
            getString(R.string.email), Context.MODE_PRIVATE
        )

        val email: String = prefs.getString(getString(R.string.email), "").toString()
        val clientPos = AirTableClient()
        namePos = clientPos.getPos(email)

        table = intent.getStringExtra("Table").toString()
        warehouse = intent.getStringExtra("Warehouse").toString()

        val recyclerView = findViewById<RecyclerView>(R.id.rcProducts)
        val btOk = findViewById<Button>(R.id.butOk)
        val eData = findViewById<Button>(R.id.eData)

        listProduct = fillList()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ProductsAdapter(listProduct, this)

        btOk.setOnClickListener {
            eData.requestFocus()
            val client = AirTableClient()
            client.addNewRequest(table, namePos)
            client.addNewPack(table, listProduct)
        }
        setNewListProduct(listProduct)
    }

    private fun fillList(): List<Product> {
        val client = AirTableClient()
        return client.getProductsFromTable(warehouse)
    }

    fun setNewListProduct(_listProduct: List<Product>) {
        println("#sa")
        listProduct = _listProduct.filter { it.count > 0f }
    }

    fun menu(view: View) {
    val menuIntent = Intent(this, MenuRequestActivity::class.java)
    startActivity(menuIntent)
    finish()
    }

}