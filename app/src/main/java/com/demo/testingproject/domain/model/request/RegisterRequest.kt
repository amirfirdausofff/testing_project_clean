package com.demo.testingproject.domain.model.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterRequest(
    val email: String,
    val password: String,
    val name: String,
    val marketingOptIn: Boolean,
) : Parcelable
