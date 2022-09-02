package com.splyza.testapp.data.repository


import com.splyza.testapp.core.network.Resource
import com.splyza.testapp.data.model.InviteTeamRequest
import com.splyza.testapp.data.model.InviteTeamResponse
import com.splyza.testapp.data.model.TeamResponse
import com.splyza.testapp.data.webservice.TeamService
import com.splyza.testapp.domain.repository.MainRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@ActivityRetainedScoped
class MainRepositoryImpl @Inject constructor(
    private val apiService: TeamService,
    private val dispatcher: CoroutineDispatcher
) : MainRepository {

    override suspend fun getTeamInfo(id: String): Resource<TeamResponse> {
        /**
        // due to there is no real api that's why off the below line of code
        // and generate random data with always success return
        return safeApiCall(dispatcher) {
        apiService.getTeamInfo("")
        }
         */
        val members = TeamResponse.Members(
            (100..105).random(),
            (10..15).random(),
            (1..10).random(),
            (1..5).random(),
            (10..100).random(),
            (0..20).random()
        )
        val limit = TeamResponse.Plan(20, 20)
        return Resource.Success(TeamResponse(id, members, limit))
    }

    override suspend fun getTeamInviteURL(
        id: String,
        role: InviteTeamRequest
    ): Resource<InviteTeamResponse> {
        /**
        // due to there is no real api that's why off the below line of code
        // and generate random data with always success return
        return safeApiCall(dispatcher) {
        apiService.setMemberRole(id, role)
        }
         */

        val invite = InviteTeamResponse("https://www.google.com/${role.roleType}")
        return Resource.Success(invite)
    }

}