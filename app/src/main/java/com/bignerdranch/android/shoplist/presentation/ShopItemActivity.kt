package com.bignerdranch.android.shoplist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.shoplist.R
import com.bignerdranch.android.shoplist.domain.ShopItem
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ShopItemActivity : AppCompatActivity() {
//    private lateinit var viewModel: ShopItemViewModel
//    private lateinit var til_name: TextInputLayout
//    private lateinit var et_name: TextInputEditText
//    private lateinit var til_count: TextInputLayout
//    private lateinit var et_count: TextInputEditText
//    private lateinit var button_save: Button
//    private var screenMode = UNKNOWN_MODE_SCREEN
//    private var shopItemId = ShopItem.UNDEFINED_ID


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
//        parseIntent()
//        initViews()
//        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
//
//        addTextChangeListener()
//        launchRightMode()
//        observeViewModel()
    }

//    private fun observeViewModel() {
//        viewModel.errorInputName.observe(this) {
//            val message = if (it) {
//                "Input name incorrectly"
//            } else {
//                null
//            }
//            til_name.error = message
//        }
//        viewModel.errorInputCount.observe(this) {
//            val message = if (it) {
//                "Input count incorrectly"
//            } else {
//                null
//            }
//            til_count.error = message
//        }
//        viewModel.shouldCloseScreen.observe(this) {
//            finish()
//        }
//    }
//
//    private fun launchRightMode() {
//        when (screenMode) {
//            MODE_ADD -> addShopItem()
//            MODE_EDIT -> editShopItem()
//        }
//    }
//
//    private fun addTextChangeListener() {
//        et_count.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                viewModel.resetErrorInputCount()
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//            }
//
//        })
//        et_name.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                viewModel.resetErrorInputName()
//
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//            }
//
//        })
//    }
//
//    private fun addShopItem() {
//        button_save.setOnClickListener {
//            viewModel.addShopItem(et_name.text?.toString(), et_count.text?.toString())
//        }
//    }
//
//    private fun editShopItem() {
//        viewModel.getShopItem(shopItemId)
//        viewModel.shopItem.observe(this) {
//            val name = it.name
//            val count = it.count.toString()
//            et_name.setText(name)
//            et_count.setText(count)
//        }
//        button_save.setOnClickListener {
//            viewModel.editShopItem(et_name.text?.toString(), et_count.text?.toString())
//        }
//
//    }
//
//    private fun parseIntent() {
//        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
//            throw RuntimeException("Is not mode screen")
//        }
//        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
//        if (mode != MODE_ADD && mode != MODE_EDIT) {
//            throw RuntimeException("Mode screen unknown")
//        }
//        screenMode = mode
//        if (screenMode == MODE_EDIT && !intent.hasExtra(EXTRA_ITEM_ID)) {
//            throw RuntimeException("Is not id item")
//        }
//        if (screenMode == MODE_EDIT) {
//            shopItemId = intent.getIntExtra(EXTRA_ITEM_ID, ShopItem.UNDEFINED_ID)
//        }
//    }
//
//    private fun initViews() {
//        til_name = findViewById(R.id.til_name)
//        et_name = findViewById(R.id.et_name)
//        til_count = findViewById(R.id.til_count)
//        et_count = findViewById(R.id.et_count)
//        button_save = findViewById(R.id.button_save)
//    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "screen_mode"
        private const val EXTRA_ITEM_ID = "item_id"

        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val UNKNOWN_MODE_SCREEN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_ITEM_ID, shopItemId)
            return intent
        }
    }
}