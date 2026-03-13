package com.example.practice2.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.practice2.navigation.EmployeeDetails
import com.example.practice2.repository.EmployeeListRepository
import com.example.practice2.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    val repository: EmployeeListRepository,
    val savedStateHandle: SavedStateHandle
): ViewModel() {

    val route = savedStateHandle.toRoute<EmployeeDetails>()
    val uuid = route.uuid
    val  detailsState = MutableStateFlow<DetailState>(DetailState.Loading)

    init {
        fetchEmployee()
    }

    private fun fetchEmployee() {
        viewModelScope.launch {
            repository.fetchEmployee(uuid).onSuccess{ result ->
                if(result == null) {
                    detailsState.value = DetailState.Empty
                } else {
                    detailsState.value = DetailState.Success(result)
                }
            }
        }
    }
}