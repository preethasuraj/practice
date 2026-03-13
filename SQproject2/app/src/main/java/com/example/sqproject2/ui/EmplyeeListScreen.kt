package com.example.sqproject2.ui

import android.util.Log
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
import androidx.compose.material3.CardElevation
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
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import com.example.sqproject2.R
import com.example.sqproject2.network.Employee

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeListScreen(vm: EmployeeListViewModel = hiltViewModel()) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    val isRefreshing by remember {
        derivedStateOf { uiState is UiState.Loading }
    }

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        state = rememberPullToRefreshState(),
        onRefresh = {vm.fetchEmployees()}
    ) {
        when(val _uiState =uiState) {
            UiState.EmptyState -> {
                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center
                    ) {
                    Text("Empty")

                }
            }
            is UiState.Error -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    _uiState.error?.let { Text(it) }
                    Button(onClick = { vm.fetchEmployees() }) {
                        Text("Retry")
                    }
                }
            }
            UiState.Loading -> {
                CircularProgressIndicator()
            }
            is UiState.Success -> {
                LazyColumn() {
                    items(
                        items= _uiState.employees,
                        key = {it.email})
                     {
                            EmployeeRow(it)
                    }
                }
            }
        }
    }
}

@Composable
fun EmployeeRow(employee: Employee){
    Card(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.padding(5.dp)) {
                AsyncImage(
                    model = employee.smallUrl,
                    contentDescription = " TODO()",

                    modifier = Modifier.clip(CircleShape),
                            onState = { state ->
                        if (state is AsyncImagePainter.State.Error) {
                            Log.e("CoilError", "Error: ${state.result.throwable}")
                        }
                    }
                )
                Column(modifier = Modifier.padding(10.dp)) {
                    employee.name?.let { Text(it) }
                }
            }
        }
    }

