package com.bignerdranch.android.shoplist.domain

class GetShopListUseCase (private val shopItemRepository: ShopItemRepository) {

    fun getShopList(): List<ShopItem>{
        return shopItemRepository.getShopList()
    }
}