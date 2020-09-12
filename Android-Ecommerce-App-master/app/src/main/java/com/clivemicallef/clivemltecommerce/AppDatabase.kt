package com.clivemicallef.clivemltecommerce

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.clivemicallef.clivemltecommerce.CartDao
import com.clivemicallef.clivemltecommerce.model.CartProduct


@Database(entities = [(CartProduct::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun cartDao():CartDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context:Context): AppDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "roomdb")
                        .build()
            }

            return INSTANCE as AppDatabase
        }
    }
}