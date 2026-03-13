package com.example.sqproject2.nav
import kotlinx.serialization.Serializable
@Serializable
sealed class Screen(val route: String) {

    data object EmployeeListScreen: Screen("Employee List")
}