package com.bignerdranch.android.shoplist.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bignerdranch.android.shoplist.R
import com.bignerdranch.android.shoplist.domain.ShopItem

class ShopListAdapter: androidx.recyclerview.widget.ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onLongClickListener: ((ShopItem) -> Unit)? = null
    var onClickListener: ((ShopItem) -> Unit)? = null

    override fun getItemViewType(position: Int): Int {
        val status = if (getItem(position).enabled){
            ENABLED
        } else{
            DISABLED
        }
        return status
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {

        val shopItemId = when(viewType){
            ENABLED -> R.layout.item_shop_enabled
            DISABLED -> R.layout.item_shop_disabled
            else -> throw java.lang.RuntimeException("Is not layout")
        }

        val view = LayoutInflater.from(parent.context).inflate(
            shopItemId,
            parent,
            false
        )
        return ShopItemViewHolder(view)
    }


    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {

        val shopItem = getItem(position)
        holder.view.setOnLongClickListener {
            onLongClickListener?.invoke(shopItem)
            true
        }
        holder.view.setOnClickListener {
            onClickListener?.invoke(shopItem)
        }

        holder.tv_text.text = shopItem.name
        holder.tv_count.text = shopItem.count.toString()

    }

    interface OnLongClickListener{
        fun onLongClick(shopItem: ShopItem)
    }
companion object{
    const val ENABLED = 100
    const val DISABLED = 101
    const val MAX_POOL_SIZE = 10
}

}