package com.example.practice3.network

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class EmployeeDto(
    @SerializedName("full_name")
    val name: String,
    val uuid: String,
    @SerializedName("photo_url_small")
    val smallUrl: String,
    @SerializedName("photo_url_large")
    val largeUrl: String,
)

data class EmployeeResponse(
    val employees: List<EmployeeDto>
)