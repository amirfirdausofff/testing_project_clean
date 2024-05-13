package com.demo.testingproject.domain.model.general

import android.os.Parcelable
import com.demo.testingproject.util.emptyString
import kotlinx.parcelize.Parcelize

@Parcelize
data class FailedResult(
    val title: String = emptyString(),
    val description: String = emptyString()
) : Parcelable
