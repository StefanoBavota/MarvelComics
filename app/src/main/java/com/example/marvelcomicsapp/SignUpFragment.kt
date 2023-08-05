package com.example.marvelcomicsapp

import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.marvelcomicsapp.databinding.FragmentSignUpBinding
import com.example.marvelcomicsapp.db.UserMarvel
import com.example.marvelcomicsapp.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editEmail.doOnTextChanged { text, _, _, _ ->
            binding.butLogin.isEnabled =
                text.toString().isNotEmpty() && binding.editPassword.text.toString()
                    .isNotEmpty() && binding.editName.text.toString()
                    .isNotEmpty()
        }

        binding.editPassword.doOnTextChanged { text, _, _, _ ->
            binding.butLogin.isEnabled =
                text.toString().isNotEmpty() && binding.editEmail.text.toString()
                    .isNotEmpty() && binding.editName.text.toString()
                    .isNotEmpty()
        }

        binding.editName.doOnTextChanged { text, _, _, _ ->
            binding.butLogin.isEnabled =
                text.toString().isNotEmpty() && binding.editEmail.text.toString()
                    .isNotEmpty() && binding.editPassword.text.toString()
                    .isNotEmpty()
        }

        binding.butLogin.setOnClickListener {
            val keyboard =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            keyboard.hideSoftInputFromWindow(binding.root.windowToken, 0)
            lifecycleScope.launch {
                loginViewModel.insertUser(
                    UserMarvel(
                        binding.editName.text.toString(),
                        binding.editEmail.text.toString(),
                        binding.editPassword.text.toString()
                    )
                )
            }
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }
}
