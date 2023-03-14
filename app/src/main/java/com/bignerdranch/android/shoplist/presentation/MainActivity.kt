package com.bignerdranch.android.shoplist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.shoplist.R
import com.bignerdranch.android.shoplist.domain.ShopItem


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var ll:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ll = findViewById(R.id.ll)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        viewModel.shopList.observe(this
        ) {
           showList(it)

        }
    }
    fun showList(list: List<ShopItem>){
        ll.removeAllViews()
        for (shopItem in list){

            val itemShopId = if ( shopItem.enabled ){
                R.layout.item_shop_enabled
            } else {
                R.layout.item_shop_disabled
            }
            val view = LayoutInflater.from(this)
                .inflate(itemShopId,ll,false )
            val tv_text = view.findViewById<TextView>(R.id.tv_name)
            val tv_count = view.findViewById<TextView>(R.id.tv_count)
            tv_text.text = shopItem.name
            tv_count.text = shopItem.count.toString()
            view.setOnLongClickListener {
                viewModel.changeEnabledState(shopItem)
                true
            }
            ll.addView(view)
        }

    }
}