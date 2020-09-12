package com.clivemicallef.clivemltecommerce.model

import com.google.gson.annotations.SerializedName

data class Product(
        @SerializedName("name")
        val title: String,

        @SerializedName("photo_url")
        val photoUrl: String,

        @SerializedName("price")
        val price: Double
)