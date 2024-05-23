package com.demo.testingproject.domain.model.general

import android.os.Parcelable
import com.demo.testingproject.util.emptyString
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorResponse(
    @SerializedName("hydra:title")
    val errorTitle: String = emptyString(),
    @SerializedName("hydra:description")
    val errorDescription: String = emptyString()
) : Parcelable
