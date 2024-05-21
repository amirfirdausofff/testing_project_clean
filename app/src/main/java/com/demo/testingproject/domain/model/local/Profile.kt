package com.demo.testingproject.domain.model.local

import android.os.Parcelable
import com.demo.testingproject.util.emptyString
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class Profile(
    @SerializedName("id")
    val id: String? = emptyString(),
    @SerializedName("gender")
    val gender: String? = emptyString(),
    @SerializedName("phoneMobile")
    val phoneNumber: String? = emptyString(),
    @SerializedName("country")
    val country: String? = emptyString(),
    @SerializedName("state")
    val state: String? = emptyString(),
    @SerializedName("dob")
    val dateBirth: String? = emptyString(),
    @SerializedName("nationality")
    val nationality: String? = emptyString(),
    @SerializedName("createdAt")
    val createdAt: String? = emptyString(),
    @SerializedName("updatedAt")
    val updatedAt: String? = emptyString(),
    @SerializedName("leaderboardScoreTotal")
    val leaderboardScoreTotal: String? = emptyString()

) : Parcelable
