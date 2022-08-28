package com.splyza.testapp.presentation.home

import com.splyza.testapp.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel<IHomeNavigator>() {

//@Inject constructor(private val repository: AppRepository)

    fun onClickInviteMember() {
        navigator.openInviteMemberScreen()
    }
}