package com.bignerdranch.android.shoplist.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.shoplist.R

class ShopItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    val tv_text = view.findViewById<TextView>(R.id.tv_name)
    val tv_count = view.findViewById<TextView>(R.id.tv_count)
}
