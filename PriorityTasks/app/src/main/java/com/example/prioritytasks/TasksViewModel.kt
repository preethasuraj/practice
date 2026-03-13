package com.example.prioritytasks

import androidx.lifecycle.ViewModel
import com.example.prioritytasks.Entity.Priority
import com.example.prioritytasks.Entity.Task
import com.example.prioritytasks.Entity.next
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.update

class TasksViewModel : ViewModel() {
    private val _tasksList = MutableStateFlow(
        listOf(
            Task("Task1", Priority.LOW),
            Task("Task2", Priority.MEDIUM),
            Task("Task3", Priority.HIGH),
            Task("Task4", Priority.MEDIUM),
        )
    )
    val taskList = _tasksList.asStateFlow()
    fun togglePriority(task: Task) {
        _tasksList.update {
            it.map { t ->
                if (t.title == task.title) {
                    t.next()
                } else t
            }
        }
    }
}