package com.clivemicallef.clivemltecommerce

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clivemicallef.clivemltecommerce.model.CartProduct
import kotlinx.android.synthetic.main.fragment_cart.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CartFragment : Fragment() {

    private fun updateCartView(view: View, products: List<CartProduct>) {
        view.recycler_view.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = CartProductsAdapter(products)
            view.progressBar.visibility = View.GONE
        }
        view.checkout.isEnabled = products.isNotEmpty()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_cart, container, false)
        val activity: CartActivity = this.activity as CartActivity
        val dao = AppDatabase.getInstance(activity.applicationContext).cartDao()
        doAsync {
            val products = dao.getAll()
            uiThread {
                updateCartView(root, products)
            }
        }

        root.checkout.setOnClickListener {
            doAsync {
                dao.deleteAll()
                val products = dao.getAll()
                uiThread {
                    updateCartView(root, products)
                    AlertDialog.Builder(activity)
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
                val products = dao.getAll()
                uiThread {
                    root.recycler_view.apply {
                        layoutManager = GridLayoutManager(activity, 2)
                        adapter = CartProductsAdapter(products)
                        root.progressBar.visibility = View.GONE
                    }
                }
            }
        }


        return root
    }
}
