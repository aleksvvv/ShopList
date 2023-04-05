package com.bignerdranch.android.shoplist.data.di

import android.app.Application
import com.bignerdranch.android.shoplist.data.database.AppDataBase
import com.bignerdranch.android.shoplist.data.database.ShopListDAO
import com.bignerdranch.android.shoplist.data.repository.ShopItemRepositoryImpl
import com.bignerdranch.android.shoplist.domain.ShopItemRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindRepository(impl: ShopItemRepositoryImpl): ShopItemRepository

    companion object{
        @Provides
        fun provApiService(application: Application): ShopListDAO{
            return AppDataBase.getInstance(application).shopListDAO()
        }
    }
}