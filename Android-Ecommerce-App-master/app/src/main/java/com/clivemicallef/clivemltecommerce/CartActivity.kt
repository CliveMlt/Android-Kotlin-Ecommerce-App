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
        setContentView(R.layout.content_main)
        supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, CartFragment())
                .commit()
    }
}