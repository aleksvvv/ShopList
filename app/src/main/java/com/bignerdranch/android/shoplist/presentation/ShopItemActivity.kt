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
    private var screenMode = UNKNOWN_MODE_SCREEN
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        parseIntent()
            if (savedInstanceState == null){
                launchRightMode()
            }
    }

    private fun launchRightMode() {
    val fragment = when (screenMode) {
            MODE_ADD ->  ShopItemFragment.getInstanceAddItem()
            MODE_EDIT -> ShopItemFragment.getInstanceEditItem(shopItemId)
        else ->  throw RuntimeException("Mode screen unknown")
        }
        supportFragmentManager.beginTransaction()
        .replace(R.id.fragment_container,fragment).commit()
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Is not mode screen")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Mode screen unknown")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT && !intent.hasExtra(EXTRA_ITEM_ID)) {
            throw RuntimeException("Is not id item")
        }
        if (screenMode == MODE_EDIT) {
            shopItemId = intent.getIntExtra(EXTRA_ITEM_ID, ShopItem.UNDEFINED_ID)
        }
    }

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