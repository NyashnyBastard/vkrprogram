package com.example.test1.rc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.R
import com.example.test1.chekList.CheckListActivity
import com.example.test1.models.CheckItem

class CheckListAdapter(val checkList: List<CheckItem>, _context:Context):RecyclerView.Adapter<CheckListAdapter.PlantHolder>() {
    var context = _context
    class PlantHolder(item: View):RecyclerView.ViewHolder(item) {
        val textTitle:TextView = item.findViewById(R.id.textTitle)
        val checkYes:RadioButton = item.findViewById(R.id.checkYes)
        val checkNo:RadioButton = item.findViewById(R.id.checkNo)
        val checkNone:RadioButton = item.findViewById(R.id.checkNone)
        fun bind(checkItem: CheckItem, context: Context) {
            textTitle.text = checkItem.title
            when (checkItem.state) {
                0 -> {
                    checkNone.isChecked = true
                }
                1 -> {
                    checkYes.isChecked = true
                }
                2 -> {
                    checkNo.isChecked = true
                }

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.checklist_item, parent, false)

        return PlantHolder(view)

    }

    override fun onBindViewHolder(holder: PlantHolder, position: Int) {
        val checkItem = checkList.get(position)
        holder.bind(checkItem, context)
        holder.checkYes.setOnClickListener {
            checkItem.state = 1
            (context as CheckListActivity).setNewCheckList(checkList)
        }
        holder.checkNo.setOnClickListener {
            checkItem.state = 2
            (context as CheckListActivity).setNewCheckList(checkList)
        }
        //println("##"+checkList.filter { it -> it.state != 0 })
    }


    override fun getItemCount(): Int {
        return checkList.size
    }
}