package com.splyza.testapp.data.repository


import com.splyza.testapp.core.network.Resource
import com.splyza.testapp.core.network.safeApiCall
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
        return safeApiCall(dispatcher) {
            apiService.getTeamInfo("")
        }
    }

}