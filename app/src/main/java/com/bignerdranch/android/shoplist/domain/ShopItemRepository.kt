package com.bignerdranch.android.shoplist.domain

import androidx.lifecycle.LiveData

interface ShopItemRepository {

    suspend fun addShopItem(shopItem: ShopItem)
    suspend fun deleteShopItem(shopItem: ShopItem)
    suspend fun editShopItem(shopItemId: ShopItem)
    suspend fun getShopItem(shopItemId: Int): ShopItem
    fun getShopList(): LiveData<List<ShopItem>>
}