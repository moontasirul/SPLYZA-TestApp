package com.splyza.testapp.data.model

import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("id") val id: String,
    @SerializedName("members") val members: Members,
    @SerializedName("plan") val plan: Plan
) {
    data class Members(
        @SerializedName("total") val total: Int,
        @SerializedName("administrators") val administrators: Int,
        @SerializedName("managers") val managers: Int,
        @SerializedName("editors") val editors: Int,
        @SerializedName("members") val members: Int,
        @SerializedName("supporters") val supporters: Int
    )

    data class Plan(
        @SerializedName("memberLimit") val memberLimit: Int,
        @SerializedName("supporterLimit") val supporterLimit: Int
    )
}
