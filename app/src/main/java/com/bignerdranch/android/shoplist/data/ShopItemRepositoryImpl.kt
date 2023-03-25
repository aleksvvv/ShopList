package com.bignerdranch.android.shoplist.data

import android.app.Application
import androidx.lifecycle.LiveData

import androidx.lifecycle.MediatorLiveData
import com.bignerdranch.android.shoplist.domain.ShopItem
import com.bignerdranch.android.shoplist.domain.ShopItemRepository

class ShopItemRepositoryImpl(application: Application): ShopItemRepository{

    private val dataBase = AppDataBase.getInstance(application)
    private val shopListDAO = dataBase.shopListDAO()
    private val  map = ShopListMapper()

    override suspend fun addShopItem(shopItem: ShopItem) {
        shopListDAO.addShopItem(map.mapShopItemToDbModel(shopItem))
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
       shopListDAO.removeShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
      shopListDAO.addShopItem(map.mapShopItemToDbModel(shopItem))
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        val dbModel = shopListDAO.getShopItem(shopItemId)
        return map.mapDbModelToShopItem(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem>>
    {
        return    MediatorLiveData<List<ShopItem>>().apply {
            addSource(shopListDAO.getShopList()){
               value = map.mapListDbModelToListShopItem(it)
            }
        }

    }
}