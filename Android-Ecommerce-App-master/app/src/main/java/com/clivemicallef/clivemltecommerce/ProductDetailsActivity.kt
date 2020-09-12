package com.clivemicallef.clivemltecommerce

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.clivemicallef.clivemltecommerce.model.CartProduct
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_details.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ProductDetailsActivity : AppCompatActivityWithDb() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        val title = intent.getStringExtra("title")
        val photoUrl = intent.getStringExtra("photo_url")
        val price = intent.getStringExtra("price")



        product_name.text = title
        product_price.text = price + " Tokens"
        Picasso.get().load(photoUrl).into(photo)

        availability.setOnClickListener {
            doAsync {
                super.db.cartDao().insertAll(CartProduct(null, title, photoUrl, price.toDouble()))
            }
            AlertDialog.Builder(this)
                    .setMessage("$title has been added to your cart!")
                    .setPositiveButton("OK") { _, _ ->
                        finish()
                    }
                    .create()
                    .show()
        }
    }
}