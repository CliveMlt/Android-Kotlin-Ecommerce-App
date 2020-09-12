package com.clivemicallef.clivemltecommerce

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.clivemicallef.clivemltecommerce.model.CartProduct
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.product_details.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CartActivity : AppCompatActivityWithDb() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_cart)


        doAsync {
            for (product in super.db.cartDao().getAll()) {
                cartText.append("${product.name} - ${product.price} TOK\n")
            }
        }

        checkout.setOnClickListener {
            val context = this
            doAsync {
                super.db.cartDao().deleteAll()
                uiThread {
                    AlertDialog.Builder(context)
                            .setMessage("Your order has been successfully placed!")
                            .setPositiveButton("OK") { _, _ ->
                                cartText.text = ""
                            }
                            .create()
                            .show()
                }

            }
        }


        clear.setOnClickListener {
            val context = this
            doAsync {
                super.db.cartDao().deleteAll()
                uiThread {
                    cartText.text = ""
                }
            }
        }

    }
}