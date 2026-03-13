package com.example.practice3.network

import com.google.gson.annotations.SerializedName

data class Employee(
    @SerializedName("full_name")
    val name : String?,
    val id: String,
    @SerializedName("photo_url_small")
    val smallUrl: String?,
    val email: String?
)

data class EmployeeResponse(
    val employees: List<Employee>
)