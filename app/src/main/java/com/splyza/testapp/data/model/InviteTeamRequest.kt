package com.splyza.testapp.data.model

import com.squareup.moshi.Json

data class InviteTeamRequest(@Json(name = "role") var roleType: String)
