package com.example.uberchicks.ui.login

import android.widget.AdapterView
import androidx.recyclerview.widget.ListAdapter
import com.example.uberchicks.domain.Product
import com.example.uberchicks.network.UserDto
import com.example.uberchicks.ui.productlist.ProductListAdapter

class LoginAdapter(val listener: AdapterView.OnItemClickListener):
    ListAdapter<UserDto, LoginAdapter.LoginViewHolder> {
}