package com.bignerdranch.android.shoplist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.shoplist.R
import com.bignerdranch.android.shoplist.databinding.FragmentShopItemBinding
import com.bignerdranch.android.shoplist.domain.ShopItem
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import javax.inject.Inject

class ShopItemFragment : Fragment() {
    private lateinit var viewModel: ShopItemViewModel

    private var _binding: FragmentShopItemBinding? = null
    val binding: FragmentShopItemBinding
        get() = _binding ?: throw RuntimeException("FragmentShopItemBinding == null")

    private lateinit var onEditingFinishedListener: OnEditingFinishedListener

    private var screenMode: String = UNKNOWN_MODE_SCREEN
    private var shopItemId: Int = ShopItem.UNDEFINED_ID

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as ShopApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
        Log.d("ShopItemFragment", "onAttach")
        if (context is OnEditingFinishedListener) {
            onEditingFinishedListener = context
        } else {
            throw RuntimeException("Activity must implement interface OnEditingFinishedListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
        Log.d("ShopItemFragment", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("ShopItemFragment", "onCreateView")
        _binding = FragmentShopItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("ShopItemFragment", "onViewCreated")

        viewModel = ViewModelProvider(this, viewModelFactory)[ShopItemViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        addTextChangeListener()
        launchRightMode()
        observeViewModel()
    }

    override fun onStart() {
        super.onStart()
        Log.d("ShopItemFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ShopItemFragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ShopItemFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ShopItemFragment", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("ShopItemFragment", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ShopItemFragment", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("ShopItemFragment", "onDetach")
    }

    private fun observeViewModel() {
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            onEditingFinishedListener.onEditingFinished()
        }
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_ADD -> addShopItem()
            MODE_EDIT -> editShopItem()
        }
    }

    private fun addTextChangeListener() {
        binding.etCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        binding.etName.addTextChangedListener(object : TextWatcher {
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
        binding.buttonSave.setOnClickListener {
            viewModel.addShopItem(
                binding.etName.text?.toString(),
                binding.etCount.text?.toString()
            )
        }
    }

    private fun editShopItem() {
        viewModel.getShopItem(shopItemId)
        binding.buttonSave.setOnClickListener {
            viewModel.editShopItem(
                binding.etName.text?.toString(),
                binding.etCount.text?.toString()
            )
        }
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
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

    interface OnEditingFinishedListener {
        fun onEditingFinished()
    }

    companion object {
        private const val SCREEN_MODE = "screen_mode"
        private const val ITEM_ID = "item_id"

        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val UNKNOWN_MODE_SCREEN = ""

        fun getInstanceAddItem(): ShopItemFragment {

            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun getInstanceEditItem(shopItemId: Int): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(ITEM_ID, shopItemId)
                }
            }
        }
    }
}