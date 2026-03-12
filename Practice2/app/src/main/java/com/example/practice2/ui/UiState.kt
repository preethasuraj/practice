package com.example.practice2.ui

import com.example.practice2.network.EmployeesList

/**
 * Represents the state to be mapped to UI
 */
sealed class UiState() {
    data object Loading: UiState()
    data object Empty: UiState()
    data class Error(val message: String): UiState()
    data class Success(val employees: EmployeesList): UiState()
}