package com.bignerdranch.android.shoplist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ShopListDAO {

    @Query("SELECT * FROM shop_items")
    fun getShopList(): LiveData<List<ShopItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addShopItem(shopListDbModel: ShopItemDbModel)

    @Query("DELETE FROM shop_items WHERE id=:shopItemId")
    fun removeShopItem(shopItemId: Int)

    @Query("SELECT * FROM shop_items WHERE id=:shopItemId LIMIT 1")
    fun getShopItem(shopItemId: Int): ShopItemDbModel
}