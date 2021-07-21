package com.gustavo.wubalubadubdub.ui.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.appbar.AppBarLayout
import com.gustavo.wubalubadubdub.R
import com.gustavo.wubalubadubdub.base.BaseActivity
import com.gustavo.wubalubadubdub.databinding.ActivityMainBinding
import com.gustavo.wubalubadubdub.ui.fragments.characters.CharacterListFragment
import com.gustavo.wubalubadubdub.ui.viewmodel.MainActions
import com.gustavo.wubalubadubdub.ui.viewmodel.MainViewModel
import com.gustavo.wubalubadubdub.ui.viewmodel.MainViewModelFactory
import com.gustavo.wubalubadubdub.utils.BaseEvent
import com.gustavo.wubalubadubdub.utils.EventObserver
import com.gustavo.wubalubadubdub.utils.RxEvent
import com.gustavo.wubalubadubdub.utils.extensions.*
import org.greenrobot.eventbus.Subscribe


class MainActivity : BaseActivity<MainViewModel, MainViewModelFactory>(), MainActions, EventObserver {

    private val charactersFragment = CharacterListFragment()

    private val fm = supportFragmentManager
    private lateinit var mBinding: ActivityMainBinding
    private var searchAppBarLayout: LinearLayout? = null
    private var appBar: AppBarLayout? = null
    private var container: View? = null
    private var edtSearch: EditText? = null
    private var searchToolBar: Toolbar? = null
    private var firstSearch = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.initMainActions(this)
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mBinding.lifecycleOwner = this
        mBinding.viewModel = mViewModel
        mViewModel.mutableTitle.value = getString(R.string.characters)
        mBinding.executePendingBindings()
        searchAppBarLayout = mBinding.searchAppBarLayout
        appBar = mBinding.appBar
        container = mBinding.container
        edtSearch = mBinding.edtSearch
        searchToolBar = mBinding.searchToolBar
        initializeUi()
        fm.beginTransaction().add(R.id.main_container, charactersFragment, "1")
            .show(charactersFragment).commit()
    }

    override fun searchCharacter() {
        searchAppBarLayout?.showSearchBar(
            appBar,
            container,
            object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator) {
                    container?.let {
                        val params = it.layoutParams
                        params.height = it.height
                        it.layoutParams = params
                    }
                }

                override fun onAnimationEnd(animation: Animator) {
                    appBar?.let {
                        it.visibility = View.GONE
                        edtSearch?.requestFocus()
                        showKeyboard(edtSearch)
                    }
                }
            })
        firstSearch = true
    }

    @Subscribe
    override fun onMessageEvent(event: BaseEvent) {
        when (event) {
            is RxEvent.EventMessageRef -> {
                toast(event.messageId)
            }

            is RxEvent.EventMessage -> {
                toast(event.message)
            }

        }
    }


    fun onHideSearch() {
        searchAppBarLayout?.hideSearchBar(
            appBar,
            container,
            object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animator: Animator) {
                }

                override fun onAnimationEnd(animator: Animator) {
                    searchAppBarLayout?.visibility = View.GONE

                    if (container != null) {
                        val params = container?.layoutParams
                        params?.height =
                            container?.height
                        container?.layoutParams = params
                    }
                    edtSearch?.setText("")

                    charactersFragment.clearList()
                    charactersFragment.mViewModel.updateSearchTerm(null)
                    mViewModel.characterSearchTerm.postValue(null)
                    charactersFragment.mViewModel.getCharacterList()
                    charactersFragment.initList()
                }
            })
    }

    @SuppressLint("PrivateResource")
    private fun initSearchBar() {
        if (searchToolBar != null) {
            searchToolBar?.setNavigationIcon(R.drawable.abc_ic_ab_back_material)
            searchToolBar?.navigationIcon?.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.toolbar_colors
                ), PorterDuff.Mode.SRC_IN
            )
            searchAppBarLayout?.visibility = View.GONE
            searchToolBar?.setNavigationOnClickListener {
                onHideSearch()
            }

            edtSearch?.makeClearableEditText(null, null)
            edtSearch?.searchWithDelay(500) {
                if (searchAppBarLayout != null && searchAppBarLayout?.visibility == View.VISIBLE)
                    charactersFragment.mViewModel.updateSearchTerm(it)
                    mViewModel.characterSearchTerm.postValue(it)
            }
        }
    }

    private fun onSearch(term: String? = null) {
        term?.let {
            charactersFragment.clearList()
            charactersFragment.mViewModel.getCharacterList()
            charactersFragment.initList()
            firstSearch = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.characterSearchTerm.removeObservers(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun initializeUi() {
        initSearchBar()
        mViewModel.characterSearchTerm.obs(this, ::onSearch)
    }
}
