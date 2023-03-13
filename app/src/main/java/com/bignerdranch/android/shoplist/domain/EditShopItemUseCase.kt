package com.bignerdranch.android.shoplist.domain

class EditShopItemUseCase(private val shopItemRepository: ShopItemRepository) {

    fun editShopItem(shopItem: ShopItem){
        shopItemRepository.editShopItem(shopItem)
    }
}