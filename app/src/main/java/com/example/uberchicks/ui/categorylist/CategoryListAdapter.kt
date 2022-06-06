package com.example.uberchicks.ui.categorylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.uberchicks.databinding.ItemCategoryBinding
import com.example.uberchicks.domain.Category
import com.example.uberchicks.domain.CategoryUi
import com.example.uberchicks.ui.productlist.ProductListAdapter

class CategoryListAdapter(val productListener:ProductListAdapter.OnItemClickListener) :
    ListAdapter<CategoryUi, CategoryListAdapter.CategoryUiListViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryUiListViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return CategoryUiListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryUiListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding(currentItem)
    }

    inner class CategoryUiListViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binding(currentItem: CategoryUi) {
            binding.apply {
                textviewCategoryName. text = currentItem.categoryName
                val productAdapter = ProductListAdapter(productListener)
                recyclerViewCategories.layoutManager = LinearLayoutManager(binding.root.context,
                    LinearLayoutManager.HORIZONTAL, false)
                productAdapter.submitList(currentItem.products)
                recyclerViewCategories.adapter = productAdapter

            }

        }

    }

    class DiffCallBack : DiffUtil.ItemCallback<CategoryUi>() {
        override fun areItemsTheSame(oldItem: CategoryUi, newItem: CategoryUi): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CategoryUi, newItem: CategoryUi): Boolean {
            return oldItem == newItem
        }

    }
}