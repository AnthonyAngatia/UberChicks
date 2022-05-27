package com.example.uberchicks.ui.categorylist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uberchicks.R
import com.example.uberchicks.databinding.FragmentCategoryListBinding
import com.example.uberchicks.network.asDomainObject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryListFragment : Fragment(R.layout.fragment_category_list) {

    private val viewModel: CategoryListViewModel by viewModels<CategoryListViewModel>()
    private lateinit var binding: FragmentCategoryListBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCategoryListBinding.bind(view)

        val categoryListAdapter = CategoryListAdapter()

        binding.recyclerViewCategoryList.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = categoryListAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            //Get categories
            val categoryList = viewModel.getCategories().map {
                it.asDomainObject()
            }
            categoryListAdapter.submitList(categoryList)
        }
    }
}