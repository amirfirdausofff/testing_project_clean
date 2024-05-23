package com.demo.testingproject.widget.dialog

import android.content.Context
import android.os.Bundle
import com.demo.testingproject.databinding.ErrorMessageDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class ErrorMessageDialog(
    context: Context,
    private val title: String,
    private val description: String,
    private val onOkClickListener: (() -> Unit)? = null
) : BottomSheetDialog(context) {

    private lateinit var binding: ErrorMessageDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ErrorMessageDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTitle.text = title
        binding.tvDes.text = description

        binding.button.setOnClickListener {
            onOkClickListener?.invoke()
            dismiss()
        }
    }
}