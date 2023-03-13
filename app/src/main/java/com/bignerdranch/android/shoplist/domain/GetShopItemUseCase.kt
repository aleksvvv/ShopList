package com.bignerdranch.android.shoplist.domain

class GetShopItemUseCase(private val shopItemRepository: ShopItemRepository) {

    fun getShopItem(shopItemId: Int): ShopItem{
        return shopItemRepository.getShopItem(shopItemId)
    }
}