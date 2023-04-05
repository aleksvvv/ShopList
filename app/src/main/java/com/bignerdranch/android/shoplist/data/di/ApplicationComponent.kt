package com.bignerdranch.android.shoplist.data.di

import android.app.Application
import com.bignerdranch.android.shoplist.presentation.MainActivity
import com.bignerdranch.android.shoplist.presentation.ShopItemFragment
import dagger.BindsInstance
import dagger.Component


@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: ShopItemFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}