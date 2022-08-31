package com.splyza.testapp.domain.useCase

import com.splyza.testapp.data.model.InviteTeamRequest
import com.splyza.testapp.domain.repository.MainRepository
import javax.inject.Inject

class TeamInvitationInfo @Inject constructor(private val repository: MainRepository) {

    suspend fun getTeamInfo(teamId: String) = repository.getTeamInfo(teamId)
    suspend fun invokeInvitation(teamId: String, role: InviteTeamRequest) =
        repository.getTeamInviteURL(teamId, role)
}