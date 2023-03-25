package com.bignerdranch.android.shoplist.domain

class EditShopItemUseCase(private val shopItemRepository: ShopItemRepository) {

    suspend fun editShopItem(shopItem: ShopItem){
        shopItemRepository.editShopItem(shopItem)
    }
}