package com.gustavo.wubalubadubdub.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel, F : ViewModelProvider.Factory, VDB : ViewDataBinding>: Fragment() {
    lateinit var mActivity: BaseActivity<VM, *>
    lateinit var mViewModel: VM
    @Inject
    lateinit var mViewModelFactory: F
    lateinit var mBinding: VDB

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun viewTitle(): Int?
    protected abstract fun getViewModelClass(): Class<VM>
    abstract fun initializeUi()

    open fun setTitle() {
        viewTitle()?.let {
            mActivity.supportActionBar?.title = getString(it)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity as BaseActivity<VM, *>
        mViewModel =
            ViewModelProvider(this, mViewModelFactory).get(getViewModelClass())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setTitle()
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return mBinding.root
    }
}