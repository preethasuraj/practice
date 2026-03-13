package com.example.practice2.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice2.database.EmployeeEntity
import com.example.practice2.network.Employee
import com.example.practice2.network.EmployeesList
import com.example.practice2.repository.EmployeeListRepository
import com.example.practice2.util.toEmployee
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.Converter
import javax.inject.Inject

@HiltViewModel
class EmployeeListViewModel @Inject constructor(
    val repository: EmployeeListRepository
): ViewModel() {

    private val _error = MutableStateFlow<String?>(null)
     val uiState = combine( repository.employees, _error) { employees, error ->

         when {
             employees.isEmpty() && _error.value != null ->
                 UiState.Error( _error.value ?: "")

             employees.isEmpty() -> UiState.Empty
             else -> UiState.Success(EmployeesList(employees.toEmployees()))
         }
     }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState.Loading
        )


    private val _refreshing = MutableStateFlow(false)
     val refreshing = _refreshing.asStateFlow()

    init {
        fetchData()
    }

    fun fetchData() {
        if(_refreshing.value) {
            return
        }
        _refreshing.value = true
        _error.value = null
        viewModelScope.launch {
            try {
                repository.fetchEmployees()
                    .onFailure {
                        _error.value = it.message
                    }
            }finally {
                _refreshing.value = false
            }
        }

    }
}

private fun List<EmployeeEntity>.toEmployees(): List<Employee> {
    return this.map {
        it.toEmployee()
    }
}


