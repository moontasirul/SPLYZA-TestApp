package com.splyza.testapp.presentation.inviteMember

import androidx.lifecycle.viewModelScope
import com.splyza.testapp.core.base.BaseViewModel
import com.splyza.testapp.core.network.onError
import com.splyza.testapp.core.network.onLoading
import com.splyza.testapp.core.network.onSuccess
import com.splyza.testapp.data.model.InviteTeamRequest
import com.splyza.testapp.domain.useCase.TeamInvitationInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class InviteMemberViewModel @Inject constructor(private val team: TeamInvitationInfo) :
    BaseViewModel<IInviteMemberNavigator>() {

    var currentMembers = MutableStateFlow("0")
    var currentSupporters = MutableStateFlow("0")
    var memberLimit = MutableStateFlow("0")
    var supporterLimit = MutableStateFlow("0")

    var isMemberFull = false
    var isSupporterFull = false

    var teamMember = arrayListOf("Coach", "Player Coach", "Player", "Supporter")


    var maxMemberLimit = MutableStateFlow(0)
    var maxSupporterLimit = MutableStateFlow(0)

    var minSupporterLimit = MutableStateFlow(0)


    var isSupporterAvailable = MutableStateFlow(true)

    var invitationURL = MutableStateFlow("https://example.com/ti/eyJpbnZpdGVJZ")

    init {
        viewModelScope.launch {
            getTeam()
        }
    }

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


    private fun getTeam() {
        viewModelScope.launch {
            team.getTeamInfo("").onSuccess {
                // set data to stateflow

                currentMembers.value = it.members.members.toString()
                currentSupporters.value = it.members.supporters.toString()

                maxMemberLimit.value = it.plan.memberLimit
                memberLimit.value = maxMemberLimit.value.toString()
                maxSupporterLimit.value = it.plan.supporterLimit
                supporterLimit.value = maxSupporterLimit.value.toString()
            }.onError { e, _ ->
                // TODO: handle api error
                Timber.d(e)
            }.onLoading {
                // TODO: loading
                Timber.d("Loading")
            }
        }
    }


    fun setInvitationRole(role: InviteTeamRequest) {
        viewModelScope.launch {
            team.invokeInvitation("", role).onSuccess {
                // set data to stateflow
                invitationURL.value = it.inviteURL
            }.onError { e, _ ->
                // TODO: handle api error
                Timber.d(e)
            }.onLoading {
                // TODO: loading
                Timber.d("Loading")
            }
        }
    }

}