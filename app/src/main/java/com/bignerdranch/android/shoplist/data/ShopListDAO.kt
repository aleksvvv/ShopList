package com.bignerdranch.android.shoplist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bignerdranch.android.shoplist.domain.ShopItem

@Dao
interface ShopListDAO {

@Query("SELECT * FROM shopItems")
    fun getShopItemList():LiveData<List<ShopItemDBModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addShopItem(shopItemDBModel: ShopItemDBModel)

    @Query("SELECT FROM shopItems WHERE id =: shopItemId")
    fun removeShopItem(shopItemId: Int)

    @Query("SELECT FROM shopItems WHERE id =: shopItemId")
    fun getShopItem(shopItemId: Int):ShopItemDBModel

}