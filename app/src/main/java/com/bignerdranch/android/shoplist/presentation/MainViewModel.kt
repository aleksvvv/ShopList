package com.bignerdranch.android.shoplist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.shoplist.data.ShopItemRepositoryImpl
import com.bignerdranch.android.shoplist.domain.DeleteShopItemUseCase
import com.bignerdranch.android.shoplist.domain.EditShopItemUseCase
import com.bignerdranch.android.shoplist.domain.GetShopListUseCase
import com.bignerdranch.android.shoplist.domain.ShopItem

class MainViewModel: ViewModel() {

    private val repository  = ShopItemRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList(){
       val list = getShopListUseCase.getShopList()
        shopList.value = list
    }
    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
        getShopList()
    }

    fun changeEnableState(shopItem: ShopItem){
        val item = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(item)
        getShopList()
    }
}