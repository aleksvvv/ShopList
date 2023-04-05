package com.bignerdranch.android.shoplist.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShopItemDbModel::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun shopListDAO(): ShopListDAO

    companion object {
        private var INSTANCE: AppDataBase? = null
        private val NAME_DB = "shop_item.db"
        private val LOCK = Any()

        fun getInstance(application: Application): AppDataBase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDataBase::class.java,
                    NAME_DB
                )
                    .build()

                INSTANCE = db
                return db
            }
        }
    }
}