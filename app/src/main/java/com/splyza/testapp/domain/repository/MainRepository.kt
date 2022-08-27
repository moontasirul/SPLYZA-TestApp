package com.splyza.testapp.domain.repository

import com.splyza.testapp.core.network.Resource
import com.splyza.testapp.data.model.TeamResponse


interface MainRepository {
    suspend fun getTeamInfo(id: String): Resource<TeamResponse>
}