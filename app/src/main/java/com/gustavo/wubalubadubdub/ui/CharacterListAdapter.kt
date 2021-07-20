package com.gustavo.wubalubadubdub.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gustavo.wubalubadubdub.R
import com.gustavo.wubalubadubdub.databinding.CharacterItemBinding
import com.gustavo.wubalubadubdub.model.Characters
import com.gustavo.wubalubadubdub.ui.viewmodel.CharacterItemViewModel
import com.gustavo.wubalubadubdub.utils.extensions.load

class CharacterListAdapter(val onItemClick: (Characters?) -> Unit) : PagingDataAdapter<Characters, CharacterListAdapter.CharacterViewHolder>(
    COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_item,  parent,false)

        val binding = CharacterItemBinding.bind(view)

        return CharacterViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class CharacterViewHolder(private val binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val characterViewModel = CharacterItemViewModel()

        fun bind(characters: Characters){
            characterViewModel.bind(characters,::onItemClick)
            binding.viewModel = characterViewModel
            binding.executePendingBindings()
        }

        private fun onItemClick(characters: Characters?){
            onItemClick.invoke(characters)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Characters>() {
            override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
                return oldItem == newItem
            }
        }
    }
}