package com.example.practice2

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practice2.navigation.EmployeeDetails
import com.example.practice2.navigation.EmployeeList
import com.example.practice2.ui.EmployeeListScreen
import com.example.practice2.ui.EmployeeListViewModel
import com.example.practice2.ui.details.DetailsScreen
import com.example.practice2.ui.details.DetailsViewModel
import com.example.practice2.ui.theme.Practice2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practice2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EmployeeListNavGraph()
                }
            }
        }
    }
}

@Composable
fun EmployeeListNavGraph() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = EmployeeList()) {
        composable<EmployeeList> {
            EmployeeListScreen(
                onRowClick = { uuid: String ->
                    navController.navigate(
                        EmployeeDetails(uuid = uuid)
                    )
                }
            )
        }
        composable<EmployeeDetails> {
            DetailsScreen()
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
    Practice2Theme {
        Greeting("Android")
    }
}