package com.splyza.testapp.data.webservice


import com.splyza.testapp.data.model.TeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TeamService {

    @GET("/teams/{teamId}")
    suspend fun getTeamInfo(
        @Path("id") id: String
    ): Response<TeamResponse>

}
