package com.example.newsarticle.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsarticle.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    val repository: ArticleRepository,
) : ViewModel() {

     val uiState: StateFlow<UiState> = repository.articlesCached
        .map { articleEntities ->
            if(articleEntities.isEmpty()){
                UiState.Empty
            } else {
                UiState.Success(articleEntities)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState.Loading
        )
    val refreshing = MutableStateFlow(false)

    init {
        refreshNews()
    }

    fun refreshNews() {
        viewModelScope.launch {
            refreshing.value = true

            repository.refreshNews()
            refreshing.value = false

        }
    }

}