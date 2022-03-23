package com.example.test1.API

import com.example.test1.models.Product
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import kotlin.random.Random

class AirTableClient {
    private val token = "keymsgRIVkfzhm17E"
    private val nameApp = "app2h7tNPLSCz4YnI"
    private val url = "https://api.airtable.com/v0"
    lateinit var codePack:String

    fun addNewRequestProduct() {
        val request = HttpRequest("$url/$nameApp/Requests")
        request.setRequestBody("{\"records\": [{\"fields\": {\"DateRequest\": \"2022-03-19\"}}],\"typecast\": true}")
        request.setHeader("Authorization","Bearer $token")
        request.sendPostRequest()
        try{
            val records = JSONArray(request.getResponseBody().getString("records"))
            val record = JSONObject(records[0].toString())
            val fields = JSONObject(record.getString("fields"))
            val codePackProduct = fields.getString("CodePackProduct")
            codePack = codePackProduct
        }
        catch (e:Exception) {
            println(e.message)
        }

    }

    fun addNewPackProducts(products: List<Product>) {
        val request = HttpRequest("$url/$nameApp/PackProducts")

        var body = "{\"records\":["
        for (product in products) {
            body += "{\"fields\":{\"CodePack\":$codePack," +
                    "\"Product\":\"${product.title}\",\"Count\":\"${product.count}\"}},"
        }

        body = body.subSequence(0,body.length-1).toString()
        body+="]}"
        request.setRequestBody(body)
        request.setHeader("Authorization","Bearer $token")
        request.sendPostRequest()
    }

    fun getProductsFromWarehouse(): ArrayList<Product> {
        val request = HttpRequest("$url/$nameApp/Warehouse")
        request.setHeader("Authorization","Bearer $token")
        request.sendGetRequest()

        try {
            val listProduct = ArrayList<Product>()
            val records = JSONArray(request.getResponseBody().getString("records"))
            for (i in 0 until records.length()) {
                val record = JSONObject(records[i].toString())
                val fields = JSONObject(record.getString("fields"))
                val name = fields.getString("Name")
                listProduct.add(Product(name))
            }
            return listProduct
        }
        catch (e:Exception) {
            println(e.message)
        }
        return ArrayList<Product>()
    }
    fun getProductsFromDrinks1(): ArrayList<Product> {
        val request = HttpRequest("$url/$nameApp/Drinks1")
        request.setHeader("Authorization","Bearer $token")
        request.sendGetRequest()

        try {
            val listProduct = ArrayList<Product>()
            val records = JSONArray(request.getResponseBody().getString("records"))
            for (i in 0 until records.length()) {
                val record = JSONObject(records[i].toString())
                val fields = JSONObject(record.getString("fields"))
                val name = fields.getString("Name")
                listProduct.add(Product(name))
            }
            return listProduct
        }
        catch (e:Exception) {
            println(e.message)
        }
        return ArrayList<Product>()
    }


    fun getCodePack(): Int {
        return Random.nextInt(0, 99999999)
    }

}