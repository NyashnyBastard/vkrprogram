package com.example.test1.rc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.R
import com.example.test1.RequestProductActivity
import com.example.test1.models.Product
import org.w3c.dom.Text

class ProductsAdapter(val names: List<Product>, _context:Context):RecyclerView.Adapter<ProductsAdapter.PlantHolder>() {
    var context = _context
    class PlantHolder(item: View):RecyclerView.ViewHolder(item) {
        val textView:TextView = item.findViewById(R.id.textTitle)
        val editText:TextView = item.findViewById(R.id.editCount)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)

        return PlantHolder(view)

    }

    override fun onBindViewHolder(holder: PlantHolder, position: Int) {
        holder.textView.text = names[position].title
        holder.editText.addTextChangedListener() {
            names[position].count = it.toString().toInt()
            (context as RequestProductActivity).setNewListProduct(names)
        }
    }

    override fun getItemCount(): Int {
        return names.size
    }
    fun getListProduct(): List<Product> {
        return names
    }
}