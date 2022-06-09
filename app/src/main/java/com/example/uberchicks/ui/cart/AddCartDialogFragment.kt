package com.example.uberchicks.ui.cart

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
import com.example.uberchicks.databinding.DialogFragmentAddCartBinding
import com.example.uberchicks.domain.ProductUiModel
import com.example.uberchicks.domain.asDomainModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AddCartDialogFragment : DialogFragment() {

//    private lateinit var binding: DialogFragmentAddCartBinding
    private val viewModel: AddCartViewModel by viewModels()
    private lateinit var args: AddCartDialogFragmentArgs




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DialogFragmentAddCartBinding.inflate(inflater)
        args = AddCartDialogFragmentArgs.fromBundle(requireArguments())

        confugureThemeResources()


        binding.apply {
            textViewProductNameAC.text = args.productUI.productName
            textViewQuantityAC.text = "Quantity ${args.productUI.quantity}"
            editTextQuantity.setText("${args.productUI.quantity}")
            textViewPriceDescriptionAC.text = args.productUI.priceDescription
            val totalPrice = getPrice(args.productUI) * args.productUI.quantity!!
            textViewTotalPrice.text = totalPrice.toString()
            buttonCancel.setOnClickListener {
                dismiss()
            }
            if (args.productUI.productDiscountedPrice != null && args.productUI.productDiscountedPrice!! > 0.0) {
                textViewPriceDescriptionAC.paintFlags =
                    textViewPriceDescriptionAC.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                textViewDiscountedPriceAC.apply{
                    visibility = View.VISIBLE
                    text = "Kshs ${args.productUI.productDiscountedPrice}"
                }


            }

            buttonAdd.setOnClickListener {
                val input = binding.editTextQuantity.text.toString()
                viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                    viewModel.addToCart(args.productUI.asDomainModel(), input.toInt())
                }
                dismiss()
            }
        }
        return binding.root
    }

    private fun getPrice(productUi: ProductUiModel): Double {
        return if (productUi.productDiscountedPrice == 0.0 || productUi.productDiscountedPrice == null){
            productUi.productPrice
        }else{
            productUi.productDiscountedPrice
        }
    }

    private fun confugureThemeResources() {
        when (context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                getDialog()!!.getWindow()
                    ?.setBackgroundDrawableResource(R.drawable.rounded_corner_shape_dark);//Works better than seeting it in xml file
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                getDialog()!!.getWindow()
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

//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return AlertDialog.Builder(requireContext()).setMessage("My Message").setTitle("My Title").create()
//    }
}