package com.bignerdranch.android.shoplist.presentation

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bignerdranch.android.shoplist.R
import com.bignerdranch.android.shoplist.domain.ShopItem

class ShopListAdapter: RecyclerView.Adapter<ShopListAdapter.ShopListHolder>() {
    var onLongClickListener: OnLongClickListener? = null

    var shopList= listOf<ShopItem>()
   set(value) {
       field = value
       notifyDataSetChanged()
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

        val shopItem = shopList[position]
        holder.view.setOnLongClickListener {
            onLongClickListener?.onLongClick(shopItem)
            true
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