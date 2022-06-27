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
import android.graphics.Paint;
import android.view.View
import timber.log.Timber

class ProductListAdapter(val listener: OnItemClickListener) :
    ListAdapter<ProductUiModel, ProductListAdapter.ProductUiModelListViewHolder>(
        ProductListDiffCallBack()
    ) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductUiModelListViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductUiModelListViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ProductUiModelListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding(currentItem)

    }

    inner class ProductUiModelListViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun binding(productUi: ProductUiModel) {
//                Listen to tap on an item

            binding.root.setOnClickListener {
                Timber.i("On item clicked")
                listener.onItemClick(productUi)
            }
            binding.textViewRemoveCart.setOnClickListener {
                Timber.i("On remove item")
                listener.onRemoveClick(productUi)
            }

//                Bind views to data
            binding.apply {
                Glide.with(itemView)
                    .load(productUi.imageUrl)
                    .centerCrop()
                    .error(R.drawable.abstract_shape_2)
                    .into(imageViewProduct)
                textviewProductName.text = productUi.productName.replaceFirstChar { it.uppercase() }
                textviewPriceDescription.text = productUi.priceDescription
                if (productUi.productDiscountedPrice != null && productUi.productDiscountedPrice > 0.0) {
                    textviewPriceDescription.paintFlags =
                        textviewPriceDescription.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    textViewDiscountedPrice.apply {
                        visibility = View.VISIBLE
                        text = "Kshs ${productUi.productDiscountedPrice}"
                    }

                }
                if (productUi.quantity != null && productUi.quantity != 0) {
                    val price = getPrice(productUi)
                    val totalPrice = price * productUi.quantity!!


                    textViewAddCart.visibility = View.INVISIBLE
                    textViewRemoveCart.visibility = View.VISIBLE
                    textViewRemoveCart.text = "${productUi.quantity} items Kshs ${totalPrice}"

                }
            }
        }

        private fun getPrice(productUi: ProductUiModel): Double {
            return if (productUi.productDiscountedPrice == 0.0 || productUi.productDiscountedPrice == null) {
                productUi.productPrice
            } else {
                productUi.productDiscountedPrice
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

        fun onRemoveClick(productUi: ProductUiModel)
    }
}