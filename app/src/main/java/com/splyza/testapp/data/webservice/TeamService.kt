package com.splyza.testapp.data.webservice


import com.splyza.testapp.data.model.InviteTeamRequest
import com.splyza.testapp.data.model.InviteTeamResponse
import com.splyza.testapp.data.model.TeamResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TeamService {

    @GET("/teams/{teamId}")
    suspend fun getTeamInfo(
        @Path("teamId") teamId: String
    ): Response<TeamResponse>


    @POST("/teams/{teamId}/invites")
    suspend fun setMemberRole(
        @Path("teamId") teamId: String,
        @Body inviteRole: InviteTeamRequest
    ): Response<InviteTeamResponse>
}
