package com.bignerdranch.android.shoplist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.shoplist.data.ShopItemRepositoryImpl
import com.bignerdranch.android.shoplist.domain.AddShopItemUseCase
import com.bignerdranch.android.shoplist.domain.EditShopItemUseCase
import com.bignerdranch.android.shoplist.domain.GetShopListUseCase
import com.bignerdranch.android.shoplist.domain.ShopItem

class MainViewModel: ViewModel() {

    private val repository = ShopItemRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList(){
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }
    fun addShopItem(shopItem: ShopItem){
        addShopItemUseCase.addShopItem(shopItem)
        getShopList()
    }

    fun editShopItem(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
        getShopList()
    }
}