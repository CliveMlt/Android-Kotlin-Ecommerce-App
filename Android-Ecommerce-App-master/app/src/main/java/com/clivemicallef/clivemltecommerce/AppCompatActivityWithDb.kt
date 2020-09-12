package com.clivemicallef.clivemltecommerce

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.room.Room

open class AppCompatActivityWithDb : AppCompatActivity() {

    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize the room database
        db = AppDatabase.getInstance(applicationContext)
    }
}