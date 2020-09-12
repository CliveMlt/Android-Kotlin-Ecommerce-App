package com.clivemicallef.clivemltecommerce

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.clivemicallef.clivemltecommerce.model.Product
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_cart.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class CartFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_cart, container, false)
        val dao = AppDatabase.getInstance(activity!!.applicationContext).cartDao()
        doAsync {
            val products = dao.getAll()
            println(products)
            uiThread {
                //root.recycler_view.apply {
                //    layoutManager = GridLayoutManager(activity, 2)
                //    adapter = CartProductsAdapter(products)
                //    root.progressBar.visibility = View.GONE
                //}
            }
        }

        root.checkout.setOnClickListener {
            doAsync {
                dao.deleteAll()
                uiThread {
                    AlertDialog.Builder(activity!!.applicationContext)
                        .setMessage("Your order has been successfully placed!")
                        .setPositiveButton("OK") { _, _ ->

                        }
                        .create()
                        .show()
                }

            }
        }


        root.clear.setOnClickListener {
            val context = this
            doAsync {
                dao.deleteAll()
                uiThread {
                }
            }
        }


        return root
    }
}
