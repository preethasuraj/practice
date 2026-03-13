package com.example.newsarticle.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.room.Room

@Composable
fun ArticlesScreen(vm: ArticleViewModel) {
    val uiState: UiState by vm.uiState.collectAsStateWithLifecycle()
    val isRefreshing: Boolean by vm.refreshing.collectAsStateWithLifecycle()

    ArticlesScreenStateLess(uiState, {vm.refreshNews()}, isRefreshing)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesScreenStateLess(state: UiState, onRefresh: () ->Unit, isRefreshing: Boolean) {
    when(state) {
        UiState.Empty -> Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Empty State")
        }
        UiState.Loading -> {

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is UiState.Success -> {
            PullToRefreshBox(
                onRefresh = onRefresh,
                isRefreshing = isRefreshing
            ) {
                LazyColumn() {
                    items(
                        items = state.articles,
                        key = {it.url}
                    ) {
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .padding(3.dp),
                            elevation = CardDefaults.cardElevation(3.dp),
                            ) {

                            Row(Modifier
                                .fillMaxWidth()
                                .padding(3.dp),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically) {
                                Text(it.url,
                                    style = MaterialTheme.typography.bodyMedium)

                            }

                        }
                    }
                }


            }

        }
    }
}