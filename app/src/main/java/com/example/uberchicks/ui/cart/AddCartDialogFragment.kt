package com.example.uberchicks.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.uberchicks.R
import com.example.uberchicks.databinding.DialogFragmentAddCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCartDialogFragment : DialogFragment() {

//    private lateinit var binding: DialogFragmentAddCartBinding
    private val viewModel: AddCartViewModel by viewModels()
    private lateinit var args: AddCartDialogFragmentArgs




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DialogFragmentAddCartBinding.inflate(inflater)
        args = AddCartDialogFragmentArgs.fromBundle(requireArguments())

        getDialog()!!.getWindow()
            ?.setBackgroundDrawableResource(R.drawable.rounded_corner_shape);//Works better than seeting it in xml file
        binding.buttonCancel.setOnClickListener {
            dismiss()
        }
        val input = binding.editTextQuantity.text.toString()
        binding.buttonAdd.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                viewModel.addToCart(args.product, input.toInt())
            }


        }
        return binding.root
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