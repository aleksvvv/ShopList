package com.bignerdranch.android.shoplist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bignerdranch.android.shoplist.R
import com.bignerdranch.android.shoplist.databinding.ItemShopDisabledBinding
import com.bignerdranch.android.shoplist.databinding.ItemShopEnabledBinding
import com.bignerdranch.android.shoplist.domain.ShopItem

class ShopListAdapter :
    androidx.recyclerview.widget.ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onLongClickListener: ((ShopItem) -> Unit)? = null
    var onClickListener: ((ShopItem) -> Unit)? = null

    override fun getItemViewType(position: Int): Int {
        val status = if (getItem(position).enabled) {
            ENABLED
        } else {
            DISABLED
        }
        return status
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {

        val shopItemId = when (viewType) {
            ENABLED -> R.layout.item_shop_enabled
            DISABLED -> R.layout.item_shop_disabled
            else -> throw java.lang.RuntimeException("Is not layout")
        }

        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            shopItemId,
            parent,
            false
        )
        return ShopItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val binding = holder.binding
        val shopItem = getItem(position)
        binding.root.setOnLongClickListener {
            onLongClickListener?.invoke(shopItem)
            true
        }
        binding.root.setOnClickListener {
            onClickListener?.invoke(shopItem)
        }

        when (binding) {
            is ItemShopDisabledBinding -> {
                binding.shopItem = shopItem
                //                binding.tvName.text = shopItem.name
//                binding.tvCount.text = shopItem.count.toString()
            }
            is ItemShopEnabledBinding -> {
                binding.shopItem = shopItem
//                binding.tvName.text = shopItem.name
//                binding.tvCount.text = shopItem.count.toString()
            }
        }
    }

    companion object {
        const val ENABLED = 100
        const val DISABLED = 101
        const val MAX_POOL_SIZE = 10
    }

}