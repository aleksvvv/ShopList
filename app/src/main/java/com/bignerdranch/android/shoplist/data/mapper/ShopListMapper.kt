package com.bignerdranch.android.shoplist.data.mapper

import com.bignerdranch.android.shoplist.data.database.ShopItemDbModel
import com.bignerdranch.android.shoplist.domain.ShopItem
import javax.inject.Inject


class ShopListMapper @Inject constructor(){

fun mapShopItemToDbModel(shopItem: ShopItem): ShopItemDbModel {
    return ShopItemDbModel(
        id = shopItem.id,
        name = shopItem.name,
        count = shopItem.count,
        enabled = shopItem.enabled
    )
}
    fun mapDbModelToShopItem(shopItemDbModel: ShopItemDbModel):ShopItem{
        return ShopItem(
            id = shopItemDbModel.id,
            name = shopItemDbModel.name,
            count = shopItemDbModel.count,
            enabled = shopItemDbModel.enabled
        )
    }

    fun mapListDbModelToListShopItem(list: List<ShopItemDbModel>): List<ShopItem>{
        return list.map {
            mapDbModelToShopItem(it)
        }
    }
}