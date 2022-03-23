package com.example.test1.rc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.R
import com.example.test1.RequestProductActivity
import com.example.test1.models.Product
import org.w3c.dom.Text

class ProductsAdapter(val products: List<Product>, _context:Context):RecyclerView.Adapter<ProductsAdapter.PlantHolder>() {
    var context = _context
    class PlantHolder(item: View):RecyclerView.ViewHolder(item) {
        val textTitle:TextView = item.findViewById(R.id.textTitle)
        val editCount:TextView = item.findViewById(R.id.editCount)
        fun bind(product:Product, context: Context) {
            textTitle.text = product.title
            editCount.text = product.count.toString()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)

        return PlantHolder(view)

    }

    override fun onBindViewHolder(holder: PlantHolder, position: Int) {
        val product = products.get(position)
        holder.bind(product,context)
        holder.editCount.setOnFocusChangeListener { view, isFocus ->
            if (!isFocus) {
                println("#lost")
                try {
                    val product = products.get(position)
                    product.count = holder.editCount.text.toString().toInt()
                    println(products)
                    (context as RequestProductActivity).setNewListProduct(products)
                }
                catch (e:Exception) {
                    println(e.message)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return products.size
    }
    fun getListProduct(): List<Product> {
        return products
    }
}
