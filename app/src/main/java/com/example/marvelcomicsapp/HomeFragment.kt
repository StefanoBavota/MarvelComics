package com.example.marvelcomicsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.marvelcomicsapp.adapter.RecyclerViewAdapterHome
import com.example.marvelcomicsapp.adapter.RecyclerViewAdapterImage
import com.example.marvelcomicsapp.databinding.FragmentHomeBinding
import com.example.marvelcomicsapp.model.ImageModel
import com.example.marvelcomicsapp.utils.DataBoundListAdapter
import com.example.marvelcomicsapp.viewModel.HomeViewModel
import com.example.marvelcomicsapp.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapterRe: RecyclerViewAdapterHome
    private lateinit var adapterImage: RecyclerViewAdapterImage
    private val loginViewModel: LoginViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var doubleBackToExitPressedOnce = false

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (doubleBackToExitPressedOnce) {
                        val dialog = CustomDialogFragmentExit()
                        dialog.isCancelable = false
                        dialog.show(parentFragmentManager, "customDialog")
                    }
                    doubleBackToExitPressedOnce = !doubleBackToExitPressedOnce
                }
            }
        )

        homeViewModel.getUser()
        adapterRe = RecyclerViewAdapterHome()

        val imageList = ArrayList<ImageModel>()
        imageList.add(ImageModel(R.drawable.hulk, "hulk"))
        imageList.add(ImageModel(R.drawable.thanos, "thanos"))
        imageList.add(ImageModel(R.drawable.deadpool, "deadpool"))
        imageList.add(ImageModel(R.drawable.thor, "thor"))
        imageList.add(ImageModel(R.drawable.loki, "loki"))
        imageList.add(ImageModel(R.drawable.iron_man, "iron man"))

        adapterImage = RecyclerViewAdapterImage(object : DataBoundListAdapter.onItemClickListener {
            override fun onClick(name: String) {
                homeViewModel.getComicsData(name)
                homeViewModel.event.observe(viewLifecycleOwner) {
                    adapterRe.apply {
                        it?.let { it ->
                            it.forEach {
                                submitList(it)
                            }
                        }
                        notifyDataSetChanged()
                    }
                    binding.titleComicsList.apply {
                        adapter = adapterRe
                    }
                }
            }
        })

        adapterImage.apply {
            submitList(imageList)
        }

        binding.carouselRecyclerview.adapter = adapterImage
        binding.carouselRecyclerview.apply {
            set3DItem(true)
            setAlpha(true)
            setInfinite(false)
        }

        homeViewModel.userName.observe(viewLifecycleOwner) {
            binding.nameUser.text = it
        }

        binding.logout.setOnClickListener {
            loginViewModel.update()
            loginViewModel.logout()
            findNavController().navigate(R.id.actionGlobalToLoginFragment)
        }
    }
}