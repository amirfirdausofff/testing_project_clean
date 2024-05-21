package com.demo.testingproject.domain.model.local

import android.os.Parcelable
import com.demo.testingproject.util.emptyString
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class AppPreferences(
    @SerializedName("id")
    val id: String? = emptyString(),
    @SerializedName("enableLocation")
    val enableLocation: String? = emptyString(),
    @SerializedName("preferredLanguage")
    val preferredLanguage: String? = emptyString(),
    @SerializedName("iosNotificationToken")
    val iosNotificationToken: String? = emptyString(),
    @SerializedName("androidNotificationToken")
    val androidNotificationToken: String? = emptyString(),
    @SerializedName("citizenship")
    val citizenship: String? = emptyString(),
    @SerializedName("createdAt")
    val createdAt: String? = emptyString(),
    @SerializedName("updatedAt")
    val updatedAt: String? = emptyString()

) : Parcelable
