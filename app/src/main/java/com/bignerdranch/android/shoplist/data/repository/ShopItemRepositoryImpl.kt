package com.bignerdranch.android.shoplist.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.bignerdranch.android.shoplist.data.database.ShopListDAO
import com.bignerdranch.android.shoplist.data.mapper.ShopListMapper
import com.bignerdranch.android.shoplist.domain.ShopItem
import com.bignerdranch.android.shoplist.domain.ShopItemRepository
import javax.inject.Inject

class ShopItemRepositoryImpl @Inject constructor(
    private val shopListDAO: ShopListDAO,
    private val map: ShopListMapper
) : ShopItemRepository {

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

    override fun getShopList(): LiveData<List<ShopItem>> {
        return MediatorLiveData<List<ShopItem>>().apply {
            addSource(shopListDAO.getShopList()) {
                value = map.mapListDbModelToListShopItem(it)
            }
        }

    }
}