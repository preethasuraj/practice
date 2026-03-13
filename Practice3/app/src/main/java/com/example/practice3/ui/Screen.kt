package com.example.practice3.ui

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(val route: String){
    @Serializable
    data object EmployeeListScreen: Screen("EmployeeList")

}
