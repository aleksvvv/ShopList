package com.bignerdranch.android.shoplist.domain

import androidx.lifecycle.LiveData

class GetShopListUseCase (private val shopItemRepository: ShopItemRepository) {

    fun getShopList(): LiveData<List<ShopItem>>{
        return shopItemRepository.getShopList()
    }
}