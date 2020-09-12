package com.clivemicallef.clivemltecommerce

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.clivemicallef.clivemltecommerce.model.CartProduct

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_products")
    fun getAll(): MutableList<CartProduct>

    @Insert
    fun insertAll(vararg products: CartProduct)

    @Query("DELETE FROM cart_products")
    fun deleteAll()

    @Delete
    fun delete(vararg products: CartProduct)
}