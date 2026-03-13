package com.example.practice3.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.practice3.R
import com.example.practice3.network.EmployeeDto

@Composable
fun EmployeeListScreen(
    listViewModel: EmployeeListViewModel = hiltViewModel()
) {
    val uiState by listViewModel.uiState.collectAsStateWithLifecycle()

    EmployeeListScreenStateless(uiState, {listViewModel.fetchEmployees()})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeListScreenStateless(uiState: ListUiState, onRetry: () -> Unit) {
    PullToRefreshBox(
        onRefresh = {},
        isRefreshing = uiState == ListUiState.Loading,
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        when(uiState){
            ListUiState.Empty -> EmptyView()
            is ListUiState.Error -> ErrorView(
                uiState.message,
                onRetry)
            ListUiState.Loading -> LoadingView()
            is ListUiState.Success -> EmployeeListView(uiState.employees)
        }
    }
}

@Composable
fun ErrorView(message: String, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ){
        Text(
            text = message,
            style = MaterialTheme.typography.headlineSmall
            )
        Button(onClick = onClick) {
            Text(
                text = stringResource(R.string.retry),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}



@Composable
fun EmployeeListView(employees: List<EmployeeDto>) {
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)) {
        items(
            items = employees,
            key ={it.uuid}
        ) {
            EmployeeRow(it)
        }
    }
}

@Composable
fun EmployeeRow(employee: EmployeeDto) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .semantics(mergeDescendants = true, properties = {})
        .padding(5.dp),
        elevation = CardDefaults.cardElevation(3.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = employee.smallUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(60.dp)
            )
        }
    }
}

@Composable
fun LoadingView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ){
        CircularProgressIndicator()
    }
}

@Composable
fun EmptyView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ){
        Text(
            text = stringResource(R.string.empty),
            style = MaterialTheme.typography.headlineSmall
        )

    }
}