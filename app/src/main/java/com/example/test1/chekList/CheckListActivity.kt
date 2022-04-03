package com.example.test1.chekList

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.API.AirTableClient
import com.example.test1.R
import com.example.test1.models.CheckItem
import com.example.test1.models.Product
import com.example.test1.rc.CheckListAdapter
import com.example.test1.rc.ProductsAdapter
import java.util.ArrayList

class CheckListActivity : AppCompatActivity() {
    private lateinit var namePos: String
    private lateinit var table: String
    private lateinit var warehouse: String
    private lateinit var checkList: List<CheckItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist)

        var prefs = this.getSharedPreferences(
            getString(R.string.email), Context.MODE_PRIVATE)

        val recyclerView = findViewById<RecyclerView>(R.id.rcCheckList)
        val btOk = findViewById<Button>(R.id.btOk)
        checkList = fillList()

        val email:String = prefs.getString(getString(R.string.email),"").toString()
        val clientPos = AirTableClient()
        namePos = clientPos.getPos(email)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CheckListAdapter(checkList, this)

        //table = intent.getStringExtra("Table").toString()
        //warehouse = intent.getStringExtra("Warehouse").toString()

        table = "CheckList1"
        warehouse = "CheckList1"

        btOk.setOnClickListener {
            if (checkAllFilled()) {
                val client = AirTableClient()
                client.addNewReport(table, namePos)
                client.addNewReportInfo(table, checkList)
            }
            else {
                Toast.makeText(this, "Вы не заполнили один или несколько пунктов!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun fillList(): List<CheckItem> {
        val client = AirTableClient()
        return client.getCheckList("ChekList1");
    }

    fun setNewCheckList(_checkList:List<CheckItem>) {
        checkList = _checkList
    }

    fun checkAllFilled():Boolean {
        return checkList.filter { it.state == 0 }.isEmpty()
    }
}