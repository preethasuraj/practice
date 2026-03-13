package com.example.taskwhisperer.data.model

data class Task (
    val id: String = java.util.UUID.randomUUID().toString(),
    val title: String,
    val isStarred: Boolean = false,
)

data class TaskUiState (
    val taskList: List<Task> = emptyList(),
    val isLoading: Boolean = false,
)

