package com.example.marvelcomicsapp.utils

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class DataBoundListAdapter<T>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, DataBoundViewHolder>(
    AsyncDifferConfig.Builder<T>(diffCallback)
        .build()
) {
    interface onItemClickListener {
        fun onClick(name: String)
    }

    // crea un nuovo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder {
        // Ottiene l'oggetto ViewDataBinding associato alla vista dell'elemento del RecyclerView
        val binding = createBinding(parent, viewType)
        // Restituisce un nuovo DataBoundViewHolder
        return DataBoundViewHolder(binding)
    }

    // crea e restituire l'oggetto ViewDataBinding associato alla vista dell'elemento
    protected abstract fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding

    // associa i dati dell'elemento corrente all'oggetto ViewDataBinding
    override fun onBindViewHolder(holder: DataBoundViewHolder, position: Int) {
        if (position < itemCount) {
            bind(holder.binding, getItem(position))
            holder.binding.executePendingBindings()
        }
    }

    protected abstract fun bind(binding: ViewDataBinding, item: T)
}
