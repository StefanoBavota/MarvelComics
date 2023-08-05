package com.example.marvelcomicsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.marvelcomicsapp.databinding.DialogAlertHomeExitBinding

class CustomDialogFragmentExit : DialogFragment() {
    private lateinit var binding: DialogAlertHomeExitBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogAlertHomeExitBinding.inflate(layoutInflater)

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnConfirm.setOnClickListener {
            requireActivity().finish()
        }

        return binding.root
    }
}