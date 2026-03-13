package com.example.sqproject2

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sqproject2.nav.Screen
import com.example.sqproject2.ui.EmployeeListScreen
import com.example.sqproject2.ui.theme.SQproject2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SQproject2Theme {
                EmployeeListNavHost()
            }
        }
    }
}


@Composable
fun EmployeeListNavHost() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.EmployeeListScreen.route){
        composable(Screen.EmployeeListScreen.route) {
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
    SQproject2Theme {
        Greeting("Android")
    }
}