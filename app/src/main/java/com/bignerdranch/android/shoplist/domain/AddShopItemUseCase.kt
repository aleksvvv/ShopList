package com.bignerdranch.android.shoplist.domain

import javax.inject.Inject

class AddShopItemUseCase @Inject constructor(
    private val shopItemRepository: ShopItemRepository
) {

    suspend fun addShopItem(shopItem: ShopItem) {
        shopItemRepository.addShopItem(shopItem)
    }
}