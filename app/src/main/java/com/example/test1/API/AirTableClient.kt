package com.example.test1.API

import com.example.test1.models.Product
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import java.text.SimpleDateFormat
import kotlin.random.Random
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class AirTableClient {
    private val token = "keymsgRIVkfzhm17E"
    private val nameApp = "app2h7tNPLSCz4YnI"
    private val url = "https://api.airtable.com/v0"
    lateinit var codePack:String

    fun addNewRequestProduct() {
        val request = HttpRequest("$url/$nameApp/Requests")

        val sdf = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
        val nowDate = sdf.parse(Calendar.getInstance().time.toString())
        sdf.applyPattern("yyyy-MM-dd")
        val now = sdf.format(nowDate)

        request.setRequestBody("{\"records\": [{\"fields\": {\"DateRequest\": \"$now\"}}],\"typecast\": true}")
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
    fun addNewRequestDrinks() {
        val request = HttpRequest("$url/$nameApp/RequestDrinks")
        request.setRequestBody("{\"records\": [{\"fields\": {\"DateRequest\": \"2022-03-19\"}}],\"typecast\": true}")
        request.setHeader("Authorization","Bearer $token")
        request.sendPostRequest()
        try{
            val records = JSONArray(request.getResponseBody().getString("records"))
            val record = JSONObject(records[0].toString())
            val fields = JSONObject(record.getString("fields"))
            val codePackDrinks = fields.getString("CodePackDrinks")
            codePack = codePackDrinks
        }
        catch (e:Exception) {
            println(e.message)
        }

    }



    fun addNewPackProducts(products:List<Product>) {
        val request = HttpRequest("$url/$nameApp/PackProducts")
        for (i in 0..products.size step 10) {
            var end = i+10
            if (i+10>products.size) {
                end = products.size
            }
            val partProducts = products.subList(i,end)
            var body = "{\"records\":["
            for (product in partProducts) {
                product.title = product.title.replace("\"","\\\"")
                body += "{\"fields\":{\"CodePack\":$codePack," +
                        "\"Product\":\"${product.title}\",\"Count\":\"${product.count}\"}},"
            }

            body = body.subSequence(0, body.length - 1).toString()
            body += "]}"
            request.setRequestBody(body)
            request.setHeader("Authorization", "Bearer $token")
            request.sendPostRequest()
        }
    }
    fun addNewPackDrinks(products:List<Product>) {
        val request = HttpRequest("$url/$nameApp/PackDrinks")

        var body = "{\"records\":["
        for (drink in products) {
            body += "{\"fields\":{\"CodePack\":$codePack," +
                    "\"Product\":\"${drink.title}\",\"Count\":\"${drink.count}\"}},"
        }

        body = body.subSequence(0,body.length-1).toString()
        body+="]}"
        request.setRequestBody(body)
        request.setHeader("Authorization","Bearer $token")
        request.sendPostRequest()
    }
    fun addNewPackStorehouse(products:List<Product>) {
        val request = HttpRequest("$url/$nameApp/PackStorehouse")

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
    fun addNewPackDetergents(products:List<Product>) {
        val request = HttpRequest("$url/$nameApp/PackDetergents")

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
    fun getProductsFromStorehouse(): ArrayList<Product> {
        val request = HttpRequest("$url/$nameApp/Storehouse")
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
            println(e.message)        }
        return ArrayList<Product>()
    }
    fun getProductsFromDetergents(): ArrayList<Product> {
        val request = HttpRequest("$url/$nameApp/Detergents")
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
            println(e.message)        }
        return ArrayList<Product>()
    }


    fun getCodePack(): Int {
        return Random.nextInt(0, 99999999)
    }

}
