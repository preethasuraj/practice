package com.example.practice3

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practice3.ui.EmployeeListScreen
import com.example.practice3.ui.EmployeeListViewModel
import com.example.practice3.ui.Screen
import com.example.practice3.ui.UiState
import com.example.practice3.ui.theme.Practice3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val vm = ViewModelProvider.create(this).get<EmployeeListViewModel>()
        setContent {
            Practice3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EmployeeListNavGraph(vm)
                }
            }
        }
    }
}

@Composable
fun EmployeeListNavGraph(vm: EmployeeListViewModel) {
    val navController = rememberNavController()
    NavHost(navController,
        startDestination = Screen.EmployeeListScreen
        ) {
        composable<Screen.EmployeeListScreen> {
            EmployeeListScreen(
                vm.uiState,
                {vm.fetchEmployees()}
            )
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
    Practice3Theme {
        Greeting("Android")
    }
}