package com.example.uberchicks.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.uberchicks.database.CartDatabaseModel
import com.example.uberchicks.databinding.ItemCartItemBinding
import com.example.uberchicks.ui.categorylist.CategoryListAdapter

class CartAdapter:ListAdapter<CartDatabaseModel, CartAdapter.CartViewHolder>(CartComparatorCallBack()) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.binding(getItem(position))

    }

    inner class CartViewHolder(private val itemCartBinding:ItemCartItemBinding):
        RecyclerView.ViewHolder(itemCartBinding.root){

            fun binding(currentItem:CartDatabaseModel){
                itemCartBinding.apply {
                    textViewProductName.text = currentItem.productName
                    textViewCartQuantity.text = currentItem.quantity.toString()
                }

            }

    }

    private class CartComparatorCallBack:DiffUtil.ItemCallback<CartDatabaseModel>() {
        override fun areItemsTheSame(
            oldItem: CartDatabaseModel,
            newItem: CartDatabaseModel
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: CartDatabaseModel,
            newItem: CartDatabaseModel
        ): Boolean {
            return oldItem == newItem
        }
    }


}