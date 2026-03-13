package com.example.practice2.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.practice2.R
import com.example.practice2.network.Employee

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val uiState = viewModel.detailsState.collectAsStateWithLifecycle()
    when(val state =uiState.value) {
        DetailState.Empty -> {
            Empty()
        }
        DetailState.Loading -> LoadingScreen()
        is DetailState.Success -> EmployeeScreen(state.employee)
    }

}

@Composable
fun Empty() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            stringResource(R.string.empty),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }

}

@Composable
fun EmployeeScreen(employee: Employee) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp)
        .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            AsyncImage(
                model = employee.largeUrl,
                contentDescription = "Image of ${employee.name}",
                contentScale = ContentScale.Fit,
                error = painterResource(R.drawable.ic_android_black_24dp),
            )
            Text(employee.name,
                style = MaterialTheme.typography.headlineSmall)

        }

    }
}