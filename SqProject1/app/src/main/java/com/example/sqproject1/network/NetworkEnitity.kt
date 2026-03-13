package com.example.sqproject1.network

import com.google.gson.annotations.SerializedName

data class Employee(
    @SerializedName("full_name")
    val name:String?,
    val email: String?,
    val id:String,
    @SerializedName("photo_url_small")
    val imageUrlSmall: String?
)

data class EmployeeResponse(
    val employees: List<Employee>
)

