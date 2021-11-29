package com.ekotyoo.njawi.presentation.auth.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "name")
    val name: String?,

    @Json(name = "email")
    val email: String?,
)