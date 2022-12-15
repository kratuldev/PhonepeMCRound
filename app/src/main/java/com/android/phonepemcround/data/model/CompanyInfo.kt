package com.android.phonepemcround.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CompanyInfo(
    val id: Int,
    val name: String,
    val imgSource: String
)