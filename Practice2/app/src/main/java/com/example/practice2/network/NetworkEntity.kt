package com.example.practice2.network

import com.google.gson.annotations.SerializedName

data class Employee(
    @SerializedName("full_name")
    val name: String,
    @SerializedName("email_address")
    val email: String,
    val uuid: String,
    @SerializedName("photo_url_small")
    val smallUrl: String
)

data class EmployeesList(
    val employees: List<Employee>
)