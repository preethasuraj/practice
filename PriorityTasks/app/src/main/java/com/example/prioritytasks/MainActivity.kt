package com.example.prioritytasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.prioritytasks.ui.theme.PriorityTasksTheme
import com.example.prioritytasks.ui.theme.TaskScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val vm = ViewModelProvider.create(this).get<TasksViewModel>()
        setContent {
            PriorityTasksTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    TaskScreen(
                        vm.taskList, {task -> vm.togglePriority(task)}
                    )
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PriorityTasksTheme {
        Greeting("Android")
    }
}