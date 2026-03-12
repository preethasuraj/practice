package com.example.practice2.ui

import androidx.compose.runtime.snapshots.SnapshotApplyResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice2.repository.EmployeeListRepository
import com.example.practice2.ui.UiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeListViewModel @Inject constructor(
    val repository: EmployeeListRepository
): ViewModel() {
    private var _uiState = MutableStateFlow<UiState>(
        value = UiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    init {
        fetchData()
    }

    fun fetchData() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            repository.fetchEmployees()
                .onSuccess { employees ->
                    if (employees.employees.isEmpty()) {
                        _uiState.value = UiState.Empty
                    }
                    _uiState.value = Success(employees)
                }
                .onFailure {
                    _uiState.value =
                        UiState.Error(
                            it.message ?: "Error in fetching data"
                        )
                }
        }
    }
}