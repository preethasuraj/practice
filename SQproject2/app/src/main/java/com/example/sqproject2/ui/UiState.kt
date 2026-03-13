package com.example.sqproject2.ui

import com.example.sqproject2.network.Employee

sealed class UiState {
    data object EmptyState: UiState()
    data object Loading: UiState()
    data class Error(val error: String?): UiState()
    data class Success(val employees: List<Employee>): UiState()
}