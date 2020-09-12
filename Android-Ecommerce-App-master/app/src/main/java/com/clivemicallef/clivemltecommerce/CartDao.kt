package com.clivemicallef.clivemltecommerce

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.clivemicallef.clivemltecommerce.model.CartProduct

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_products")
    fun getAll(): List<CartProduct>

    @Insert
    fun insertAll(vararg users: CartProduct)
}