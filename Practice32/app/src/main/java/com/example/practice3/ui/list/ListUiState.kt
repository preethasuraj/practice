package com.example.practice3.ui.list

import com.example.practice3.network.EmployeeDto

// todo use a different entity for UI and map EmployeeDto that entity
sealed class ListUiState {
    data object Loading: ListUiState()
    data object Empty: ListUiState()
    data class Error(val message: String): ListUiState()
    data class Success(val employees: List<EmployeeDto>): ListUiState()

}