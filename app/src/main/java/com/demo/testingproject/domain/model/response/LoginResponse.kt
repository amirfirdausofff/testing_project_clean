package com.demo.testingproject.domain.model.response

import android.os.Parcelable
import com.demo.testingproject.util.emptyString
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    val token: String = emptyString(),
    @SerializedName("refresh_token")
    val refreshToken: String = emptyString()
) : Parcelable
