package com.bignerdranch.android.shoplist.domain

import javax.inject.Inject

class GetShopItemUseCase @Inject constructor(
    private val shopItemRepository: ShopItemRepository
) {

    suspend fun getShopItem(shopItemId: Int): ShopItem {
        return shopItemRepository.getShopItem(shopItemId)
    }
}