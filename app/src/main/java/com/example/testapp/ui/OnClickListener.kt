package com.example.testapp.ui

class OnClickListener(val clickListener: (list: String) -> Unit) {
    fun onClick(list: String) = clickListener(list)
}