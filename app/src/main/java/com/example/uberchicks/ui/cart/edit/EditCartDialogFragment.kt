package com.example.uberchicks.ui.cart.edit

import android.content.res.Configuration
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.uberchicks.R
import com.example.uberchicks.databinding.DialogFragmentEditCartBinding
import com.example.uberchicks.domain.ProductUiModel
import com.example.uberchicks.domain.asDomainModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditCartDialogFragment : DialogFragment(R.layout.dialog_fragment_edit_cart) {

    private lateinit var binding: DialogFragmentEditCartBinding
    private lateinit var args: EditCartDialogFragmentArgs
    private val viewModel: EditCartViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureThemeResources()
        args = EditCartDialogFragmentArgs.fromBundle(requireArguments())

        binding = DialogFragmentEditCartBinding.bind(view)
        val productName = args.productUi.productName
        val productPrice = args.productUi.productPrice
        val productDiscountedPrice = args.productUi.productDiscountedPrice
        val quantity = args.productUi.quantity ?: 1
        val imageUrl = args.productUi.imageUrl
        val id = args.productUi.id
        val priceDescription = args.productUi.priceDescription

        binding.apply {
            textViewProductNameEC.text = productName.replaceFirstChar { it.uppercase() }
            textViewQuantityEC.text = "Quantity $quantity"
            textViewTotalPriceEC.text = (getPrice(args.productUi) * quantity).toString()
            textViewPriceDescriptionEC.text = priceDescription
            if (productDiscountedPrice != null && productDiscountedPrice!! > 0.0) {
                textViewPriceDescriptionEC.paintFlags =
                    textViewPriceDescriptionEC.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                textViewDiscountedPriceEC.apply {
                    visibility = View.VISIBLE
                    text = "Kshs $productDiscountedPrice"
                }
            }
            editTextQuantityEditCart.setText(quantity.toString())

            buttonEdit.setOnClickListener {
                //Edit the product in the cart
                val input = editTextQuantityEditCart.text.toString()
                lifecycleScope.launchWhenStarted {
                    viewModel.editCart(args.productUi.asDomainModel(), input.toInt())
                }
                dismiss()
            }
            buttonRemove.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    viewModel.removeFromCart(args.productUi)
                }
                dismiss()
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

    private fun configureThemeResources() {
        when (context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                getDialog()!!.window
                    ?.setBackgroundDrawableResource(R.drawable.rounded_corner_shape_dark);//Works better than seeting it in xml file
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                getDialog()!!.window
                    ?.setBackgroundDrawableResource(R.drawable.rounded_corner_shape);//Works better than seeting it in xml file
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                getDialog()!!.getWindow()
                    ?.setBackgroundDrawableResource(R.drawable.rounded_corner_shape);//Works better than seeting it in xml file
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

}