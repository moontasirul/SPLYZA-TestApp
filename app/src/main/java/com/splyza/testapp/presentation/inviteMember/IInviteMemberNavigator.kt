package com.splyza.testapp.presentation.inviteMember

import com.splyza.testapp.core.base.IBaseNavigator


interface IInviteMemberNavigator : IBaseNavigator {
    fun onOpenShareQRCode()
    fun copyLink()
}