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

class ShopListAdapter: Adapter<ShopListAdapter.ShopListHolder>() {

    var shopList= listOf<ShopItem>()
   set(value) {
       field = value
       notifyDataSetChanged()
   }

    class ShopListHolder(view: View): ViewHolder(view){
        val tv_text = view.findViewById<TextView>(R.id.tv_name)
        val tv_count = view.findViewById<TextView>(R.id.tv_count)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListHolder {

        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_shop_disabled,
            parent,
            false
        )
        return ShopListHolder(view)
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun onBindViewHolder(holder: ShopListHolder, position: Int) {

        holder.tv_text.text = shopList[position].name
        holder.tv_count.text = shopList[position].count.toString()

        if (shopList[position].enabled){
            holder.tv_text.setTextColor(Color.RED)
        } else {
            holder.tv_text.setTextColor(Color.BLACK)
        }
    }


}