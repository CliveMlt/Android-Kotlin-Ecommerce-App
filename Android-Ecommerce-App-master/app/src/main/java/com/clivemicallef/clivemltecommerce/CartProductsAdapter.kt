package com.clivemicallef.clivemltecommerce

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.clivemicallef.clivemltecommerce.model.CartProduct
import com.clivemicallef.clivemltecommerce.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_row.view.*
import kotlinx.android.synthetic.main.product_row.view.photo
import kotlinx.android.synthetic.main.product_row.view.title
import kotlinx.android.synthetic.main.product_row_compressed.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CartProductsAdapter(private val products: MutableList<CartProduct>) : RecyclerView.Adapter<CartProductsAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: CartProductsAdapter.ViewHolder, position: Int) {
        val product = products[position]
        Picasso.get().load(product.imageUrl).into(holder.image)
        holder.title.text = product.name
        holder.price.text = product.price.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row_compressed, parent, false)
        val holder = ViewHolder(view)
        val adapter = this

        val dao = AppDatabase.getInstance(parent.context).cartDao()

        fun bindListener() {
            val intent = Intent(parent.context, ProductDetailsActivity::class.java)
            intent.putExtra("title", products[holder.adapterPosition].name)
            intent.putExtra("photo_url", products[holder.adapterPosition].imageUrl)
            intent.putExtra("price", products[holder.adapterPosition].price.toString())
            parent.context.startActivity(intent)
        }

        view.title.setOnClickListener {
            bindListener()
        }
        view.photo.setOnClickListener {
            bindListener()
        }
        view.delete.setOnClickListener {
            doAsync {
                dao.delete(products[holder.adapterPosition])

                uiThread {
                    products.remove(products[holder.adapterPosition])
                    adapter.notifyDataSetChanged()
                }
            }
        }
        return holder
    }

    override fun getItemCount() = products.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.photo)
        val title: TextView = itemView.findViewById(R.id.title)
        val price: TextView = itemView.findViewById(R.id.price)
    }
}