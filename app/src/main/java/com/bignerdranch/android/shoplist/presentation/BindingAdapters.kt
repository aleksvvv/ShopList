package com.bignerdranch.android.shoplist.presentation

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorInputName")
fun bindErrorInputName(til: TextInputLayout,state: Boolean){
    val message = if (state) {
        "Input name incorrectly"
    } else {
        null
    }
    til.error = message
}
@BindingAdapter("errorInputCount")
fun bindErrorInputCount(til: TextInputLayout,state: Boolean){
    val message = if (state) {
        "Input count incorrectly"
    } else {
        null
    }
   til.error = message
}