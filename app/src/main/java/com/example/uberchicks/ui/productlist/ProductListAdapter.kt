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

class ProductListAdapter(val listener: OnItemClickListener) :
    ListAdapter<Product, ProductListAdapter.ProductListViewHolder>(ProductListDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding(currentItem)

    }

    inner class ProductListViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun binding(product: Product) {
//                Listen to tap on an item
            binding.root.setOnClickListener {
                listener.onItemClick(product)
            }

//                Bind views to data
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

    class ProductListDiffCallBack : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onItemClick(product: Product)
    }
}