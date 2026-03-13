package com.example.sqproject1.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.sqproject1.R
import com.example.sqproject1.network.Employee
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeListScreen(vm: EmployeeListViewModel = hiltViewModel()) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    val isRefreshing  by remember {
        derivedStateOf { uiState is EmployeeUiState.Loading }
    }
    val pullToRefreshState = rememberPullToRefreshState()
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { vm.fetchEmployees() },
        state = pullToRefreshState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {

        when (val state = uiState) {
            is EmployeeUiState.Empty -> {
                EmptyScreen()
            }

            is EmployeeUiState.Error -> {
                ErrorScreen((state).message, { vm.fetchEmployees() })
            }

            is EmployeeUiState.Success -> {
                LazyColumn() {
                    items(
                        items = state.employees,
                        key = { it.id }) {
                        EmployeeRow(it)
                    }
                }
            }

            EmployeeUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun ErrorScreen(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(message)
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }

}

@Composable
fun EmptyScreen() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Text("Empty")

    }
}

@Composable
fun EmployeeRow(employee: Employee) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically){
            AsyncImage(
                model = employee.imageUrlSmall,
                contentDescription =" TODO()",
                placeholder = painterResource(R.drawable.person),
                error = painterResource(R.drawable.person),
                modifier = Modifier.clip(CircleShape)
            )
            Column() {
                employee.name?.let { Text(it) }
            }
        }
    }

}