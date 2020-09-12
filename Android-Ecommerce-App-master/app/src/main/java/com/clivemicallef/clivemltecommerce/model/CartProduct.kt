package com.clivemicallef.clivemltecommerce.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_products")
data class CartProduct(
        @PrimaryKey val uid: Int?,
        @ColumnInfo(name = "name") val name: String?,
        @ColumnInfo(name = "image_url") val imageUrl: String?,
        @ColumnInfo(name = "price") val price: Double?
)