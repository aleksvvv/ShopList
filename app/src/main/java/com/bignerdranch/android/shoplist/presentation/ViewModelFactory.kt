package com.bignerdranch.android.shoplist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val providersViewModel:
    @JvmSuppressWildcards Map<Class<out ViewModel>,Provider<ViewModel>>
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providersViewModel[modelClass]?.get() as T
    }
}