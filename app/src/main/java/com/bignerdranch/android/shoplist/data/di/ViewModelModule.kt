package com.bignerdranch.android.shoplist.data.di

import androidx.lifecycle.ViewModel
import com.bignerdranch.android.shoplist.presentation.MainViewModel
import com.bignerdranch.android.shoplist.presentation.ShopItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShopItemViewModel::class)
    fun bindShopItemViewModel (viewModel: ShopItemViewModel): ViewModel
}