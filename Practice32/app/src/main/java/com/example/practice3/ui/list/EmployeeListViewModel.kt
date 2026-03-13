package com.example.practice3.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice3.repository.EmployeeListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
data class EmployeeListViewModel @Inject constructor(
    val employeeListRepository: EmployeeListRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<ListUiState>(ListUiState.Loading)
     val uiState = _uiState.asStateFlow()

    init {
        fetchEmployees()
    }

    fun fetchEmployees() {

        _uiState.value = ListUiState.Loading
        viewModelScope.launch {
            employeeListRepository.fetchEmployees()
                .onSuccess { result ->
                    if (result.isEmpty()) {
                        _uiState.value = ListUiState.Empty
                    } else {
                        _uiState.value = ListUiState.Success(result)
                    }
                }
                .onFailure {
                    _uiState.value =
                        ListUiState.Error(it.message ?: "Unknown error in fetching data")
                }
        }
    }
}