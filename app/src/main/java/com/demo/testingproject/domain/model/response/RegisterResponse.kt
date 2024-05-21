package com.demo.testingproject.domain.model.response

import android.os.Parcelable
import com.demo.testingproject.domain.model.local.AppPreferences
import com.demo.testingproject.domain.model.local.Profile
import com.demo.testingproject.util.emptyBoolean
import com.demo.testingproject.util.emptyString
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterResponse(
    val id: String = emptyString(),
    val email: String = emptyString(),
    val name: String = emptyString(),
    val profile: List<Profile> = emptyList(),
    val profileImage: String = emptyString(),
    val appPreferences: List<AppPreferences> = emptyList(),
    val membership: String = emptyString(),
    val isMarketingOptIn: Boolean = emptyBoolean(),
    val createdAt: String = emptyString(),
    val updatedAt: String = emptyString(),
    val subscription: String = emptyString(),
) : Parcelable
