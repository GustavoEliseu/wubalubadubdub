package com.gustavo.wubalubadubdub.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
}