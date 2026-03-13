package com.example.practice2.navigation

import kotlinx.serialization.Serializable



    @Serializable
    data class EmployeeList(val destination: String  = "Employee List")
    @Serializable
    data class EmployeeDetails(val uuid: String)
