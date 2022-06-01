package com.example.uberchicks.ui.categorylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.uberchicks.databinding.ItemCategoryBinding
import com.example.uberchicks.domain.Category
import com.example.uberchicks.domain.Product
import com.example.uberchicks.ui.productlist.ProductListAdapter
import com.example.uberchicks.ui.productlist.ProductListFragmentDirections

class CategoryListAdapter(val productListener:ProductListAdapter.OnItemClickListener) :
    ListAdapter<Category, CategoryListAdapter.CategoryListViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return CategoryListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding(currentItem)
    }

    inner class CategoryListViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binding(currentItem: Category) {
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

    class DiffCallBack : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

    }
}