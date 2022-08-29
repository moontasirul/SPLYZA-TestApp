package com.splyza.testapp.data.model

import com.google.gson.annotations.SerializedName

data class InviteTeamRequest(@SerializedName("role") var roleType: String)
