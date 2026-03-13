package com.example.prioritytasks.ui.theme

import android.R
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.prioritytasks.Entity.Priority
import com.example.prioritytasks.Entity.Task
import kotlinx.coroutines.flow.StateFlow

@Composable
fun TaskScreen(
    tasks: StateFlow<List<Task>>,
    onToggle: (task: Task) -> Unit,
) {
    val onToggle = remember {
        {task: Task -> onToggle(task)}
    }

    val taskList by tasks.collectAsStateWithLifecycle()
    val count by remember {
        derivedStateOf { taskList.map { it.priority == Priority.HIGH } .count()}
    }
    TaskScreenStateLess(
        taskList,
        onToggle, count
    )
}
@Composable
@Preview
fun TaskScreenStateLess(
    tasks: List<Task> = listOf(
        Task("One", Priority.LOW),
        Task("TWO", Priority.HIGH),

        ),
    onToggle: (task: Task) -> Unit = {},
    count: Int = 5,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "$count",
            modifier = Modifier
                .padding(10.dp),
            style = MaterialTheme.typography.headlineSmall
        )

        LazyColumn() {
            items(
                items = tasks,
                key = { it.title }
            ) {
                TaskRow(it, { onToggle(it) })
            }
        }
    }
}

@Composable
fun TaskRow(task: Task, onClick: () -> Unit) {
    val animatedColor by animateColorAsState(
        targetValue = task.priority.color,
        label = "color"
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
            .semantics(
                mergeDescendants = true
            ) {},
        elevation = CardDefaults.cardElevation(3.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.headlineSmall,
            )


            IconButton(
                onClick = onClick,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "sets pr",
                    tint = animatedColor,
                    modifier = Modifier.size(48.dp)
                )
            }

        }

    }
}


