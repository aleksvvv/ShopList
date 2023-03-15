package com.bignerdranch.android.shoplist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.bignerdranch.android.shoplist.domain.ShopItem

class ShopListDiffCallback(private val oldShopItem: List<ShopItem>,
                           private val newShopItem: List<ShopItem>)
    :DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldShopItem.size
    }

    override fun getNewListSize(): Int {
        return newShopItem.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val new = newShopItem[newItemPosition]
        val old = oldShopItem[oldItemPosition]
        return new.id == old.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val new = newShopItem[newItemPosition]
        val old = oldShopItem[oldItemPosition]
        return new == old
    }
}