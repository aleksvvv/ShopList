package com.bignerdranch.android.shoplist.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetShopListUseCase @Inject constructor(
    private val shopItemRepository: ShopItemRepository
) {

    fun getShopList(): LiveData<List<ShopItem>> {
        return shopItemRepository.getShopList()
    }
}