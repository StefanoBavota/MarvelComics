package com.example.marvelcomicsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.marvelcomicsapp.R
import com.example.marvelcomicsapp.databinding.CarouselItemBinding
import com.example.marvelcomicsapp.model.ImageModel
import com.example.marvelcomicsapp.utils.DataBoundListAdapter

class RecyclerViewAdapterImage(private var itemClickListener: onItemClickListener) : DataBoundListAdapter<ImageModel>(
    diffCallback = object : DiffUtil.ItemCallback<ImageModel>() {

        override fun areItemsTheSame(old: ImageModel, aNew: ImageModel): Boolean {
            return old == aNew
        }

        override fun areContentsTheSame(old: ImageModel, aNew: ImageModel): Boolean {
            return old.image == aNew.image
        }
    }
){
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.carousel_item,
            parent,
            false
        )
    }

    override fun bind(binding: ViewDataBinding, item: ImageModel) {
        when (binding) {
            is CarouselItemBinding -> {
            Glide.with(binding.imageRecyItem).load(item.image).into(binding.imageRecyItem)
                binding.imageRecyItem.setOnClickListener {
                    itemClickListener.onClick(item.name)
                }
            }
        }
    }
}