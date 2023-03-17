package com.bignerdranch.android.shoplist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.shoplist.R
import com.bignerdranch.android.shoplist.domain.ShopItem
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ShopItemFragment: Fragment() {
    private lateinit var viewModel: ShopItemViewModel
    private lateinit var til_name: TextInputLayout
    private lateinit var et_name: TextInputEditText
    private lateinit var til_count: TextInputLayout
    private lateinit var et_count: TextInputEditText
    private lateinit var button_save: Button

    private var screenMode: String = UNKNOWN_MODE_SCREEN
    private var shopItemId: Int = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_item, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]

        addTextChangeListener()
        launchRightMode()
        observeViewModel()
    }

        private fun observeViewModel() {
        viewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it) {
                "Input name incorrectly"
            } else {
                null
            }
            til_name.error = message
        }
        viewModel.errorInputCount.observe(viewLifecycleOwner) {
            val message = if (it) {
                "Input count incorrectly"
            } else {
                null
            }
            til_count.error = message
        }
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_ADD -> addShopItem()
            MODE_EDIT -> editShopItem()
        }
    }

    private fun addTextChangeListener() {
        et_count.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        et_name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputName()

            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    private fun addShopItem() {
        button_save.setOnClickListener {
            viewModel.addShopItem(et_name.text?.toString(), et_count.text?.toString())
        }
    }

    private fun editShopItem() {
        viewModel.getShopItem(shopItemId)
        viewModel.shopItem.observe(viewLifecycleOwner) {
            val name = it.name
            val count = it.count.toString()
            et_name.setText(name)
            et_count.setText(count)
        }
        button_save.setOnClickListener {
            viewModel.editShopItem(et_name.text?.toString(), et_count.text?.toString())
        }
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)){
            throw RuntimeException("Is not mode screen")
        }

        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Mode screen unknown")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT && !args.containsKey(ITEM_ID)) {
            throw RuntimeException("Is not id item")
        }
        if (screenMode == MODE_EDIT) {
            shopItemId = args.getInt(ITEM_ID, ShopItem.UNDEFINED_ID)
        }

    }

    private fun initViews(view: View) {
        til_name = view.findViewById(R.id.til_name)
        et_name = view.findViewById(R.id.et_name)
        til_count = view.findViewById(R.id.til_count)
        et_count = view.findViewById(R.id.et_count)
        button_save = view.findViewById(R.id.button_save)
    }

    companion object {
        private const val SCREEN_MODE = "screen_mode"
        private const val ITEM_ID = "item_id"

        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val UNKNOWN_MODE_SCREEN = ""

        fun getInstanceAddItem():ShopItemFragment{

            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }
        fun getInstanceEditItem(shopItemId: Int):ShopItemFragment{
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(ITEM_ID,shopItemId)
                }
            }
        }
    }
}