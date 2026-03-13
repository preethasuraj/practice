package com.example.prioritytasks.Entity

import androidx.compose.ui.graphics.Color
import java.util.UUID
import kotlin.random.Random

data class Task (
    val title: String,
    val priority: Priority,
    val id: String = UUID.randomUUID().toString()
)

enum class Priority(val color: Color){
    LOW(Color.Blue),
    MEDIUM(Color.Red),
    HIGH(Color.Green)
}

fun Task.next(): Task {
    return this.copy(
        priority = when(priority) {
            Priority.LOW -> Priority.MEDIUM
            Priority.MEDIUM -> Priority.HIGH
            Priority.HIGH -> Priority.LOW
        }
    )
}
