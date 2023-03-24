package com.bignerdranch.android.shoplist.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bignerdranch.android.shoplist.domain.ShopItem

@Entity("shopItems")
data class ShopItemDBModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val count: Int,
    val enabled: Boolean,
   )