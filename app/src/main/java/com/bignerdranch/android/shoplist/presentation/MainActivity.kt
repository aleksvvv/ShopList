package com.bignerdranch.android.shoplist.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.shoplist.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var recycler:RecyclerView
    private lateinit var shopListAdapter: ShopListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setupRecyclerView()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this
        ) {
            shopListAdapter.submitList(it)
        }
        val buttonAdd = findViewById<FloatingActionButton>(R.id.plus)
        buttonAdd.setOnClickListener {
            val intent = ShopItemActivity.newIntentAddItem(this)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView(){
        recycler = findViewById(R.id.recycler_view)
        shopListAdapter = ShopListAdapter()
        recycler.adapter = shopListAdapter
        recycler.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.ENABLED,
            ShopListAdapter.MAX_POOL_SIZE
        )
        recycler.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.DISABLED,
            ShopListAdapter.MAX_POOL_SIZE
        )
        setOnLongClickListener()
        setOnClickListener()
        setSwipeListener()
    }

    private fun setSwipeListener() {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recycler)
    }

    private fun setOnClickListener() {
        shopListAdapter.onClickListener = {
            val intent = ShopItemActivity.newIntentEditItem(this, it.id)
            startActivity(intent)
        }
    }

    private fun setOnLongClickListener() {
        shopListAdapter.onLongClickListener = {
            viewModel.changeEnabledState(it)
        }
    }
}
