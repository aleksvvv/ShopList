package com.bignerdranch.android.shoplist.domain

import javax.inject.Inject

class DeleteShopItemUseCase @Inject constructor(
    private val shopItemRepository: ShopItemRepository
) {

    suspend fun deleteShopItem(shopItem: ShopItem) {
        shopItemRepository.deleteShopItem(shopItem)
    }
}