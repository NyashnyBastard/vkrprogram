package com.example.test1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.API.AirTableClient
import com.example.test1.databinding.ActivityRequestProductBinding
import com.example.test1.models.Product
import com.example.test1.rc.ProductsAdapter

class RequestProduct1Activity : AppCompatActivity() {
    public lateinit var binding: ActivityRequestProductBinding
    lateinit var adapter:ProductsAdapter
    lateinit var listProduct:List<Product>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_product1)

        val recyclerView = findViewById<RecyclerView>(R.id.rcProducts)
        val btOk = findViewById<Button>(R.id.butOk)
        val eData = findViewById<EditText>(R.id.eData)

        listProduct = fillList()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ProductsAdapter(fillList(), this)

        btOk.setOnClickListener {
            eData.requestFocus()
            val client = AirTableClient()
            client.addNewRequestProduct()
            client.addNewPackProducts(listProduct)
        }

    }

    private fun fillList(): List<Product> {

        val client = AirTableClient()
        return client.getProductsFromDrinks1()

    }

    public fun setNewListProduct(_listProduct:List<Product>) {
        listProduct = _listProduct.filter { it.count!=0 }
    }
}