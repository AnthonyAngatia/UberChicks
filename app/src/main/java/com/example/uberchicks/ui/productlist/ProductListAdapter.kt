package com.example.uberchicks.ui.productlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uberchicks.R
import com.example.uberchicks.databinding.ItemProductBinding
import com.example.uberchicks.domain.Product

class ProductListAdapter() :
    ListAdapter<Product, ProductListAdapter.ProductListViewHolder>(DiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false )
        return ProductListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding(currentItem)

    }

    inner class ProductListViewHolder(private val binding: ItemProductBinding)
        :RecyclerView.ViewHolder(binding.root){
            fun binding(product:Product){
                binding.apply {
                    Glide.with(itemView)
                        .load(product.imageUrl)
                        .centerCrop()
                        .error(R.drawable.abstract_shape_2)
                        .into(imageViewProduct)
                    textviewProductName.text = product.productName
                    textviewPriceDescription.text = product.priceDescription
                }


            }
    }

    class DiffCallBack : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }
}