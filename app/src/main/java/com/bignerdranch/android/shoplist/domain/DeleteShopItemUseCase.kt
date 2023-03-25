package com.bignerdranch.android.shoplist.domain

class DeleteShopItemUseCase(private val shopItemRepository: ShopItemRepository) {

    suspend fun deleteShopItem(shopItem: ShopItem){
        shopItemRepository.deleteShopItem(shopItem)
    }
}