package com.bignerdranch.android.shoplist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.shoplist.R
import com.bignerdranch.android.shoplist.domain.ShopItem


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        viewModel.shopList.observe(this
        ) {
            Log.d("Trest", it.toString())
            if (count === 0){
                count++
                val testItem = it[0]
                viewModel.deleteShopItem(testItem)
                viewModel.editShopItem(it[4])
            }

        }

    }
}