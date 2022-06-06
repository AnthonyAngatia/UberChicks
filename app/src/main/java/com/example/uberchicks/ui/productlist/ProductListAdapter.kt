package com.example.uberchicks.ui.productlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uberchicks.R
import com.example.uberchicks.databinding.ItemProductBinding
import com.example.uberchicks.domain.ProductUiModel

class ProductListAdapter(val listener:OnItemClickListener) :
    ListAdapter<ProductUiModel, ProductListAdapter.ProductUiModelListViewHolder>(ProductListDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductUiModelListViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false )
        return ProductUiModelListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductUiModelListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding(currentItem)

    }

    inner class ProductUiModelListViewHolder(private val binding: ItemProductBinding)
        :RecyclerView.ViewHolder(binding.root){


            fun binding(productUi:ProductUiModel){
//                Listen to tap on an item
                binding.root.setOnClickListener {
                    listener.onItemClick(productUi)
                }



//                Bind views to data
                binding.apply {
                    Glide.with(itemView)
                        .load(productUi.imageUrl)
                        .centerCrop()
                        .error(R.drawable.abstract_shape_2)
                        .into(imageViewProduct)
                    textviewProductName.text = productUi.productName
                    textviewPriceDescription.text = productUi.priceDescription
                    if(productUi.quantity != null){
                        textViewAddCart.text = "Remove cart ${productUi.quantity}"
//                        textViewAddCart

                    }
                }


            }
    }

    class ProductListDiffCallBack : DiffUtil.ItemCallback<ProductUiModel>() {
        override fun areItemsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onItemClick(product: ProductUiModel)
    }
}