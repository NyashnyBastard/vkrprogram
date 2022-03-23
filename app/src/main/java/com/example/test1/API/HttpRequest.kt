package com.example.test1.API

import com.github.kittinunf.fuel.Fuel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import java.io.IOException
import java.net.URL
import kotlin.concurrent.thread

class HttpRequest(_url:String) {
    val url:String = _url
    lateinit var body:String
    lateinit var header:Pair<String, String>

    var responseBody:String = ""

    fun setHeader(name: String, value:String) {
        header = Pair(name, value)
    }
    fun setRequestBody(data:String) {
        body = data
    }



    fun sendPostRequest() {
        val t = Thread {
            val (request,response,result) = Fuel.post(url)
                .appendHeader("Content-Type", "application/json; charset=utf-8")
                .appendHeader(header)
                .body(body)
                .response()
            println(request)
            println(response)
            println(result)
            println("==================================\n")
            responseBody = response.body().asString("application/json")
        }
        t.start()
        t.join()
    }

    fun sendGetRequest() {
        val t = Thread {
            val (request,response,result) = Fuel.get(url)
                .appendHeader("Content-Type", "application/json; charset=utf-8")
                .appendHeader(header)
                .response()
            println(request)
            println(response)
            println(result)
            println("==================================\n")
            responseBody = response.body().asString("application/json; charset=utf-8")
        }
        t.start()
        t.join()
    }
    fun getResponseBody():JSONObject{
        return JSONObject(responseBody)
    }

}