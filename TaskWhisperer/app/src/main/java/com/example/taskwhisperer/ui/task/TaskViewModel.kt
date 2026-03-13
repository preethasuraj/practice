package com.example.taskwhisperer.ui.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskwhisperer.data.model.Task
import com.example.taskwhisperer.data.model.TaskUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TaskViewModel: ViewModel() {
    var _uiState = MutableStateFlow(TaskUiState())
    val uiState = _uiState.asStateFlow()

    fun addTask(title:String) {
        _uiState.update { state ->
            state.copy(taskList = state.taskList +
                    Task(
                        title = title
                    ))
        }
    }

    fun toggleFav(id: String){
        _uiState.update { state ->
            state.copy(
                taskList = state.taskList.map {
                   it.copy(isStarred =  if(it.id == id) !it.isStarred else it.isStarred)
                }
            )
        }
    }
}