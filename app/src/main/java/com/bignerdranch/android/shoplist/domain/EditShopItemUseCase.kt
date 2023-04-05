package com.bignerdranch.android.shoplist.domain

import javax.inject.Inject

class EditShopItemUseCase @Inject constructor(
    private val shopItemRepository: ShopItemRepository
) {
    suspend fun editShopItem(shopItem: ShopItem) {
        shopItemRepository.editShopItem(shopItem)
    }
}