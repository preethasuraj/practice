package com.example.practice2.ui.details

import com.example.practice2.network.Employee

sealed class DetailState {
    data object Loading: DetailState()
    data object Empty: DetailState()
    data class Success(val employee: Employee): DetailState()
}