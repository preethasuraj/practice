package com.example.sqproject2.network

import com.google.gson.annotations.SerializedName


data class EmployeeList(
    val employees: List<Employee>
)
data class Employee(
    @SerializedName("full_name")
    val name: String,
    val id: String,
    val email: String,
    @SerializedName("photo_url_small")
    val smallUrl: String
)

