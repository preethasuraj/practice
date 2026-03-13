package com.example.practice3.ui

import com.example.practice3.network.Employee

sealed class UiState {
    data object Loading : UiState()
    data object Empty : UiState()
    data class Error(val message: String) : UiState()
    data class Success(
        val employees: List<Employee>
    ) : UiState()
}