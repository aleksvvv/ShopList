package com.bignerdranch.android.shoplist.data

import com.bignerdranch.android.shoplist.domain.ShopItem
import com.bignerdranch.android.shoplist.domain.ShopItemRepository

object ShopItemRepositoryImpl: ShopItemRepository{

    private val shopList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0
    init {

        for (i in 0 until  10){
            val shopItem = ShopItem("Name$i", i, true )
            shopList.add(shopItem)
        }

    }

    override fun addShopItem(shopItem: ShopItem) {

        if (shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editShopItem(shopItemId: ShopItem) {
        val oldItem = getShopItem(shopItemId.id)
        deleteShopItem(oldItem)
        addShopItem(shopItemId)

    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw java.lang.RuntimeException("Element with id $shopItemId not found")

    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }


}