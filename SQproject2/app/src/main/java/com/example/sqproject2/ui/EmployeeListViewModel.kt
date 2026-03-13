package com.example.sqproject2.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqproject2.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeListViewModel @Inject constructor(
    val employeeRepository: EmployeeRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
     val uiState = _uiState.asStateFlow()

    init {
        fetchEmployees()
    }

    fun fetchEmployees() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            employeeRepository.fetchEmployees()
                .onFailure {
                    if(it.message == "Empty") {
                        _uiState.value = UiState.EmptyState
                    } else {
                        _uiState.value = UiState.Error(it.message)
                    }
                }
                .onSuccess {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}