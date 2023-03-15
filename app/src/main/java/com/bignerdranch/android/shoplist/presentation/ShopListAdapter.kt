package com.bignerdranch.android.shoplist.presentation

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bignerdranch.android.shoplist.R
import com.bignerdranch.android.shoplist.domain.ShopItem

class ShopListAdapter: RecyclerView.Adapter<ShopListAdapter.ShopListHolder>() {
    var count = 0
    var onLongClickListener: ((ShopItem) -> Unit)? = null
    var onClickListener: ((ShopItem) -> Unit)? = null

    var shopList= listOf<ShopItem>()
   set(value) {
       val callback = ShopListDiffCallback(shopList,value)
       val diffResult = DiffUtil.calculateDiff(callback)
       diffResult.dispatchUpdatesTo(this)
       field = value

   }



    override fun getItemViewType(position: Int): Int {
        val status = if (shopList[position].enabled){
            ENABLED
        } else{
            DISABLED
        }
        return status
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListHolder {

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
        return ShopListHolder(view)
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun onBindViewHolder(holder: ShopListHolder, position: Int) {

        Log.d("Trest", (++count).toString())
        val shopItem = shopList[position]
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
    class ShopListHolder(val view: View): ViewHolder(view){
        val tv_text = view.findViewById<TextView>(R.id.tv_name)
        val tv_count = view.findViewById<TextView>(R.id.tv_count)

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