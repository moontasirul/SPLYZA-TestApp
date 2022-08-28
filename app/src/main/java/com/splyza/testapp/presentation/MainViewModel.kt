package com.splyza.testapp.presentation

import com.splyza.testapp.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() :
    BaseViewModel<IMainNavigator>() {

    private val _isLoading = MutableStateFlow(true)
    val isLoadings = _isLoading.asStateFlow()


    fun onClickBack() {
        navigator.onBackScreen()
    }
}