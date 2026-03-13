package com.example.taskwhisperer.ui.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskwhisperer.data.model.Task

@Composable
fun TaskScreen(viewModel: TaskViewModel = viewModel()){
   val state by viewModel.uiState.collectAsStateWithLifecycle()
    val textInput by rememberSaveable{ mutableStateOf("") }
    val count by remember {
        derivedStateOf { state.taskList.count{it.isStarred} }
    }

    Column(modifier = Modifier.fillMaxWidth()
        .padding(5.dp)
        .background(MaterialTheme.colorScheme.background)) {
        Text(
            text = "Tasks List",
            style = MaterialTheme.typography.headlineSmall
        )
        LazyColumn() {
            items(
                items = state.taskList,
                key = {it.id},
            ){
                TaskRow(task = it, {viewModel.toggleFav(it.id)})
            }
        }

    }

}
@Composable
fun TaskRow(task: Task, onToggle: () -> Unit){
    Text(task.title)
    IconButton(onClick = onToggle) {
        Icon(
            imageVector =  if(task.isStarred) Icons.Default.Star else Icons.Default.Star,
            contentDescription = "",
            tint = if(task.isStarred) Color.Yellow else Color.Blue)
    }

}
