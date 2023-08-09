package com.example.marvelcomicsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.example.marvelcomicsapp.R
import com.example.marvelcomicsapp.databinding.ItemCardInfoBinding
import com.example.marvelcomicsapp.utils.DataBoundListAdapter
import com.example.marvelcomicsapp.viewModel.ViewModelCardHome

class RecyclerViewAdapterHome() : DataBoundListAdapter<ViewModelCardHome>(
    diffCallback = object : DiffUtil.ItemCallback<ViewModelCardHome>() {

        override fun areItemsTheSame(old: ViewModelCardHome, aNew: ViewModelCardHome): Boolean {
            return old == aNew
        }

        override fun areContentsTheSame(old: ViewModelCardHome, aNew: ViewModelCardHome): Boolean {
            return old.description == aNew.description
        }
    }
) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_card_info,
            parent,
            false
        )
    }

    override fun bind(binding: ViewDataBinding, item: ViewModelCardHome) {
        when (binding) {
            is ItemCardInfoBinding -> {
                binding.nameItem = item
            }
        }
    }
}