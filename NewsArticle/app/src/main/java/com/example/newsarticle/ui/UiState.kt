package com.example.newsarticle.ui

import com.example.newsarticle.database.ArticleDao
import com.example.newsarticle.database.ArticleEntity

sealed class UiState {
    data object Loading: UiState()
    data object Empty: UiState()
    data class Success(val articles: List<ArticleEntity>): UiState()
}