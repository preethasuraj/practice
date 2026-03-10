package com.example.sqproject1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sqproject1.ui.EmployeeListScreen
import com.example.sqproject1.ui.Screen
import com.example.sqproject1.ui.theme.SqProject1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SqProject1Theme {
               EmployeeAppNavHost()
            }
        }
    }
}

@Composable
fun EmployeeAppNavHost() {
    val navHostController = rememberNavController()
    NavHost(navHostController, startDestination = Screen.EmployeeList) {
        composable<Screen.EmployeeList> {
            EmployeeListScreen()
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
    SqProject1Theme {
        Greeting("Android")
    }
}