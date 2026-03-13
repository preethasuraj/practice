package com.example.sqproject1.ui

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(val route: String) {

    @Serializable
    data object EmployeeList: Screen("employee list")
}