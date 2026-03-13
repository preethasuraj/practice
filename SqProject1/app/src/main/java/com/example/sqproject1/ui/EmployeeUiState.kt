package com.example.sqproject1.ui

import com.example.sqproject1.network.Employee

sealed class EmployeeUiState() {
    object Loading: EmployeeUiState()
    data class Error(val message: String): EmployeeUiState()
    data class Empty(val message: String): EmployeeUiState()
    data class Success(val employees:List<Employee>): EmployeeUiState()
}
