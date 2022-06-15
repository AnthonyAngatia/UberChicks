package com.example.uberchicks.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.uberchicks.database.CartDatabaseModel
import com.example.uberchicks.databinding.ItemCartItemBinding
import com.example.uberchicks.domain.ProductUiModel
import com.example.uberchicks.ui.categorylist.CategoryListAdapter
import timber.log.Timber

class CartAdapter(private val listener: onCartCLickListener) :
    ListAdapter<ProductUiModel, CartAdapter.CartViewHolder>(CartComparatorCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.binding(getItem(position))

    }

    inner class CartViewHolder(private val itemCartBinding: ItemCartItemBinding) :
        RecyclerView.ViewHolder(itemCartBinding.root) {

        fun binding(currentItem: ProductUiModel) {
            itemCartBinding.editButton.setOnClickListener {
                Timber.i("On edit clicked")
                listener.onCartClickListener(currentItem)
            }

            itemCartBinding.apply {
                textViewProductName.text = currentItem.productName
                textViewCartQuantity.text = currentItem.quantity.toString()
                textViewPriceDescription.text = currentItem.priceDescription
                val totalPrice = getPrice(currentItem) * (currentItem.quantity?:1)
                textViewItemTotalPrice.text = totalPrice.toString()
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

    private class CartComparatorCallBack : DiffUtil.ItemCallback<ProductUiModel>() {
        override fun areItemsTheSame(
            oldItem: ProductUiModel,
            newItem: ProductUiModel
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: ProductUiModel,
            newItem: ProductUiModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface onCartCLickListener {
        fun onCartClickListener(productUiModel: ProductUiModel)
    }


}