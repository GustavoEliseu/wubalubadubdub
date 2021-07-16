package com.gustavo.wubalubadubdub.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gustavo.wubalubadubdub.R

/**
 * A fragment representing a list of Items.
 */
class CharacterItemFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_characters_list, container, false)

        val rv = view.findViewById<RecyclerView>(R.id.characterList)
        context?.let {
            rv.layoutManager = LinearLayoutManager(it)
        }
        rv.adapter = MyCharacterItemRecyclerViewAdapter()
        return view
    }
}