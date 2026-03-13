package com.example.practice3.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice3.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeListViewModel @Inject constructor(
    val repository: EmployeeRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchEmployees()
    }

    fun fetchEmployees() {
        _uiState.value = UiState.Loading
       viewModelScope.launch {
           repository.fetchEmployees()
               .onSuccess {
                   if(it.employees.isEmpty()) {
                       _uiState.value = UiState.Empty
                   } else {
                       _uiState.value = UiState.Success(it.employees)
                   }
               }
               .onFailure {
                   _uiState.value = UiState.Error(it.message ?: "Error")
               }
       }
    }
}