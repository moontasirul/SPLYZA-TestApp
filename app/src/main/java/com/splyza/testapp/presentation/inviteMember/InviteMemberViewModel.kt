package com.splyza.testapp.presentation.inviteMember

import com.splyza.testapp.core.base.BaseViewModel
import com.splyza.testapp.data.model.InviteTeamRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class InviteMemberViewModel @Inject constructor() : BaseViewModel<IInviteMemberNavigator>() {

    var currentMembers = MutableStateFlow("80")
    var currentSupporters = MutableStateFlow("10")
    var memberLimit = MutableStateFlow("10")
    var supporterLimit = MutableStateFlow("20")

    var isMemberFull = false
    var isSupporterFull = false

    var teamMember = arrayListOf("Coach", "Player Coach", "Player", "Supporter")


    var maxMemberLimit = MutableStateFlow(100)
    var maxSupporterLimit = MutableStateFlow(20)
    var minSupporterLimit = MutableStateFlow(10)


    var isSupporterAvailable = MutableStateFlow(true)
    fun onClickShareQRCode() {
        navigator.onOpenShareQRCode()
    }

    fun checkSupporterLimit() {
        if (minSupporterLimit.value == 0) isSupporterAvailable.value = true
    }

    fun onClickCopyLink() {}


    fun permissionListHideShow() {
        when {
            currentMembers.value.toInt() == maxMemberLimit.value -> {
                isMemberFull = true
            }
            minSupporterLimit.value == 0 -> {
                teamMember.removeAt(3)
                isMemberFull = false
            }
            currentSupporters.value.toInt() == maxSupporterLimit.value -> {
                isMemberFull = false
                isSupporterFull = true
            }
        }
    }


    fun prepareUserRole(selectedItem: String): InviteTeamRequest {
        val role = InviteTeamRequest("")
        when (selectedItem) {
            "Coach" -> role.roleType = "manager"
            "Player Coach" -> role.roleType = "editor"
            "Player" -> role.roleType = "member"
            "Supporter" -> role.roleType = "readonly"
        }
        print(role.roleType)
        return role
    }
}