package com.splyza.testapp.data.model

import com.squareup.moshi.Json

data class InviteTeamResponse(@Json(name = "url") val inviteURL: String)
