package com.example.mystrava.ui.loginvm

import net.openid.appauth.ResponseTypeValues

object AuthConfig {

    const val AUTH_URI = "https://www.strava.com/oauth/mobile/authorize"
    const val TOKEN_URI = "https://www.strava.com/oauth/token"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "activity:read"

    const val CLIENT_ID = "73249"
    const val CLIENT_SECRET = "5e60ee716ffff04ae00dd51fc209399dc3f19dd0"
    const val CALLBACK_URL = "mystravaapp://skillbox/callback"
}
