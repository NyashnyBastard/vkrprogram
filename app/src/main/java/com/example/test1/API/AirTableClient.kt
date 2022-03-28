package com.example.test1.API

import com.example.test1.models.Product
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class AirTableClient {
    private val token = "keymsgRIVkfzhm17E"
    private val nameApp = "app2h7tNPLSCz4YnI"
    private val url = "https://api.airtable.com/v0"
    lateinit var codePack:String

    fun addNewRequest(table: String,pos:String) {
        val request = HttpRequest("$url/$nameApp/Requests$table")

        val sdf = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
        val timezone =  TimeZone.getTimeZone("GMT+5");
        val cal = Calendar.getInstance(timezone)
        cal.add(Calendar.HOUR, -1)
        val nowDate = sdf.parse(cal.time.toString())
        sdf.applyPattern("yyyy-MM-dd HH:mm")
        val now = sdf.format(nowDate)

        request.setRequestBody("{\"records\": [{\"fields\": {\"DateRequest\": \"$now\"," +
                "\"NamePos\": \"$pos\"}}],\"typecast\": true}")
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

    fun addNewPack(table:String,products:List<Product>) {
        val request = HttpRequest("$url/$nameApp/Pack$table")
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

    fun getProductsFromTable(table: String): ArrayList<Product> {
        val request = HttpRequest("$url/$nameApp/$table")
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
    fun getPos(email:String): String {
        val request = HttpRequest("$url/$nameApp/NameAndEmail?filterByFormula=({Email} = '$email')")
        request.setHeader("Authorization","Bearer $token")
        request.sendGetRequest()

        try {
            val listProduct = ArrayList<Product>()
            val records = JSONArray(request.getResponseBody().getString("records"))
            val record = JSONObject(records[0].toString())
            val fields = JSONObject(record.getString("fields"))
            val name = fields.getString("Name")
            return name
        }
        catch (e:Exception) {
            println(e.message)
        }
        return ""
    }

    fun getCodePack(): Int {
        return Random.nextInt(0, 99999999)
    }

}
