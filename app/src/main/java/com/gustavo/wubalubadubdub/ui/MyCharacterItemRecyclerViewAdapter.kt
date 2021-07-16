package com.gustavo.wubalubadubdub.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gustavo.wubalubadubdub.databinding.CharacterItemBinding
import com.gustavo.wubalubadubdub.model.Character
import com.gustavo.wubalubadubdub.utils.extensions.load


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyCharacterItemRecyclerViewAdapter(val values: List<Character>
) : RecyclerView.Adapter<MyCharacterItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(values[position])
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val characterNameView: TextView = binding.characterName
        private val imageView: ImageView = binding.characterPhoto

        fun bind(character: Character){
            characterNameView.text = character.name
            imageView.load(character.image)
        }

        override fun toString(): String {
            return super.toString() + " '" + characterNameView.text.toString() + "'"
        }
    }

}