package com.example.sqproject1.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqproject1.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeListViewModel @Inject constructor(
    val repository: EmployeeRepository
): ViewModel() {

    private var _uiState = MutableStateFlow<EmployeeUiState>(EmployeeUiState.Loading)
    val uiState : StateFlow<EmployeeUiState> = _uiState.asStateFlow()

    init {
        fetchEmployees()
    }
    fun fetchEmployees() {
        _uiState.value = EmployeeUiState.Loading
        Log.d("Preetha", "${_uiState.value}")
        viewModelScope.launch {
            repository.fetchEmployees()
                .onSuccess {
                    android.util.Log.i("Preetha", "${it.size}")
                    _uiState.value = EmployeeUiState.Success(it)
                    android.util.Log.i("Preetha", "${(_uiState.value as EmployeeUiState.Success).employees.get(0)}")
                    android.util.Log.i("Preetha", "${it.get(0)}")
                    Log.d("Preetha", "${_uiState.value}")
                }
                .onFailure {
                    if(it.message == "Empty") {
                        _uiState.value = EmployeeUiState.Empty("Empty")
                    } else {
                        _uiState.value = EmployeeUiState.Error(it.message!!)
                    }
                }
        }
    }
}