package com.splyza.testapp.domain.repository

import com.splyza.testapp.core.network.Resource
import com.splyza.testapp.data.model.InviteTeamRequest
import com.splyza.testapp.data.model.InviteTeamResponse
import com.splyza.testapp.data.model.TeamResponse


interface MainRepository {
    suspend fun getTeamInfo(id: String): Resource<TeamResponse>
    suspend fun getTeamInviteURL(id: String, role: InviteTeamRequest): Resource<InviteTeamResponse>
}