package com.gustavo.wubalubadubdub.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.gustavo.wubalubadubdub.databinding.CharacterItemBinding
import com.gustavo.wubalubadubdub.model.Characters
import com.gustavo.wubalubadubdub.utils.extensions.load


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyCharacterItemRecyclerViewAdapter() : PagingDataAdapter<Characters, MyCharacterItemRecyclerViewAdapter.CharacterViewHolder>(
    COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {

        return CharacterViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class CharacterViewHolder(binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val characterNameView: TextView = binding.characterName
        private val imageView: ImageView = binding.characterPhoto

        fun bind(characters: Characters){
            characterNameView.text = characters.name
            imageView.load(characters.image)
        }

        override fun toString(): String {
            return super.toString() + " '" + characterNameView.text.toString() + "'"
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