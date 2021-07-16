package com.gustavo.wubalubadubdub.ui.fragments.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gustavo.wubalubadubdub.R
import com.gustavo.wubalubadubdub.databinding.FragmentCharactersListBinding
import com.gustavo.wubalubadubdub.ui.MyCharacterItemRecyclerViewAdapter
import com.gustavo.wubalubadubdub.ui.viewmodel.CharacterListViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * A fragment representing a list of Items.
 */
class CharacterItemFragment : Fragment() {

    private val mDisposable = CompositeDisposable()


    private var mViewModel: CharacterListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mBinding = FragmentCharactersListBinding.inflate(inflater, container, false)
        val mAdapter = MyCharacterItemRecyclerViewAdapter()

        val rv = view?.findViewById<RecyclerView>(R.id.characterList)
        context?.let {
            rv?.layoutManager = LinearLayoutManager(it)
            rv?.adapter = mAdapter
        }
        mViewModel?.let { vm->
            mDisposable.add(vm.getCharacterList().subscribe {paging ->
                mAdapter.submitData(lifecycle, paging)
            })
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mDisposable.dispose()
    }
}