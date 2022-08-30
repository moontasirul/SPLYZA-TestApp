package com.splyza.testapp.presentation.inviteMember

import com.splyza.testapp.core.base.BaseViewModel
import com.splyza.testapp.data.model.InviteTeamRequest
import com.splyza.testapp.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class InviteMemberViewModel @Inject constructor(val repository: MainRepository) :
    BaseViewModel<IInviteMemberNavigator>() {

    var currentMembers = MutableStateFlow((0..10).random().toString())
    var currentSupporters = MutableStateFlow((0..10).random().toString())
    var memberLimit = MutableStateFlow((0..10).random().toString())
    var supporterLimit = MutableStateFlow((0..10).random().toString())

    var isMemberFull = false
    var isSupporterFull = false

    var teamMember = arrayListOf("Coach", "Player Coach", "Player", "Supporter")


    var maxMemberLimit = MutableStateFlow((0..20).random())
    var maxSupporterLimit = MutableStateFlow((0..20).random())

    var minSupporterLimit = MutableStateFlow(0)


    var isSupporterAvailable = MutableStateFlow(true)


    fun onClickShareQRCode() {
        navigator.onOpenShareQRCode()
    }

    fun checkSupporterLimit() {
        if (minSupporterLimit.value == 0) isSupporterAvailable.value = true
    }

    fun onClickCopyLink() {
        navigator.copyLink()
    }


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


    fun prepareInvitationURL() {

    }


    suspend fun dummyValue() {
        repository.getTeamInfo("2112121").data
    }

}