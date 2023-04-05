package com.bignerdranch.android.shoplist.presentation

import android.app.Application
import com.bignerdranch.android.shoplist.data.di.DaggerApplicationComponent

class ShopApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}