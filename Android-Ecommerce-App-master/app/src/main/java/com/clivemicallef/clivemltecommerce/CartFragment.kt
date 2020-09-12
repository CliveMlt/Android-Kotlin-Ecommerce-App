package com.clivemicallef.clivemltecommerce

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clivemicallef.clivemltecommerce.model.CartProduct
import kotlinx.android.synthetic.main.fragment_cart.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CartFragment : Fragment() {

    private fun updateCartView(view: View, products: MutableList<CartProduct>) {
        view.recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
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
                            activity.finish()
                        }
                        .create()
                        .show()
                }

            }
        }


        root.clear.setOnClickListener {
            doAsync {
                dao.deleteAll()
                val products = dao.getAll()
                uiThread {
                    updateCartView(root, products)
                }
            }
        }


        return root
    }
}
