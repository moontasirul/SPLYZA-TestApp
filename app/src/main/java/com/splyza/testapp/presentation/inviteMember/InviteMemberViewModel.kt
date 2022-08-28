package com.splyza.testapp.presentation.inviteMember

import com.splyza.testapp.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class InviteMemberViewModel @Inject constructor() : BaseViewModel<IInviteMemberNavigator>() {

    var currentMembers = MutableStateFlow("83")
    var currentSupporters = MutableStateFlow("120")
    var memberLimit = MutableStateFlow("10")
    var supporterLimit = MutableStateFlow("12")



    fun onClickShareQRCode(){
        navigator.onOpenShareQRCode()
    }
    fun onClickCopyLink(){}
}