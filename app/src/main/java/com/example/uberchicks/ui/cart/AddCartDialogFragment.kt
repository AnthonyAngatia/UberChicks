package com.example.uberchicks.ui.cart

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.uberchicks.R
import com.example.uberchicks.databinding.DialogFragmentAddCartBinding
import com.example.uberchicks.ui.categorylist.CategoryListViewModel

class AddCartDialogFragment : DialogFragment() {

//    private lateinit var binding: DialogFragmentAddCartBinding
    private val viewmodel:CategoryListViewModel by viewModels({requireParentFragment()})


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DialogFragmentAddCartBinding.inflate(inflater)
        getDialog()!!.getWindow()
            ?.setBackgroundDrawableResource(R.drawable.rounded_corner_shape);//Works better than seeting it in xml file
        binding.buttonCancel.setOnClickListener {
            dismiss()
        }
        binding.buttonAdd.setOnClickListener {
            val x  = binding.editTextQuantity.text

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