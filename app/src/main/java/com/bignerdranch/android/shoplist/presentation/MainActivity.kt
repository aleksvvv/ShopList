package com.bignerdranch.android.shoplist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.shoplist.R
import com.bignerdranch.android.shoplist.domain.ShopItem


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var recycler:RecyclerView
    private lateinit var adapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this
        ) {
            adapter.shopList = it
        }
    }

    private fun setupRecyclerView(){
        recycler = findViewById(R.id.recycler_view)
        adapter = ShopListAdapter()
        recycler.adapter = adapter
        recycler.recycledViewPool.setMaxRecycledViews(ShopListAdapter.ENABLED,ShopListAdapter.MAX_POOL_SIZE)
        recycler.recycledViewPool.setMaxRecycledViews(ShopListAdapter.DISABLED,ShopListAdapter.MAX_POOL_SIZE)
    }
}