package com.gustavo.wubalubadubdub.ui.fragments.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.gustavo.wubalubadubdub.R
import com.gustavo.wubalubadubdub.base.BaseFragment
import com.gustavo.wubalubadubdub.databinding.FragmentCharactersListBinding
import com.gustavo.wubalubadubdub.model.Characters
import com.gustavo.wubalubadubdub.ui.Activity.characterDetailsIntent
import com.gustavo.wubalubadubdub.ui.viewmodel.CharacterListViewModel
import com.gustavo.wubalubadubdub.ui.viewmodel.CharacterListViewModelFactory
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.collectLatest

/**
 * A fragment representing a list of Items.
 */
class CharacterListFragment :
    BaseFragment<CharacterListViewModel, CharacterListViewModelFactory, FragmentCharactersListBinding>(),
    CharacterListActions {

    private val mDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mBinding = FragmentCharactersListBinding.inflate(inflater, container, false)

        val rvCharacters = mBinding.characterListRecyclerView
        mViewModel.getCharacterList()

        with(mViewModel.mAdapter) {

            rvCharacters.apply {
                postponeEnterTransition()
                viewTreeObserver.addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
                val snapHelper: SnapHelper = LinearSnapHelper()
                snapHelper.attachToRecyclerView(rvCharacters)
                adapter = mViewModel.mAdapter
                layoutManager =
                    GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            }

            with(mViewModel) {
                launchOnLifecycleScope {
                    charactersFlow.collectLatest { submitData(it) }
                }
            }
        }

        mViewModel.initCharacterListActions(this)

        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mDisposable.dispose()
    }

    override fun getLayoutId(): Int = R.layout.fragment_characters_list

    override fun viewTitle(): Int = R.string.characters

    override fun getViewModelClass(): Class<CharacterListViewModel> =
        CharacterListViewModel::class.java

    override fun initializeUi() {
    }

    override fun onCharacterClick(character: Characters?) {
        if (character != null) {
            activity?.startActivity(requireActivity().characterDetailsIntent(character))
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.cant_identify_character_click),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}