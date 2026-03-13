package com.example.newsarticle.repository

import androidx.room.Insert
import com.example.newsarticle.database.ArticleDao
import com.example.newsarticle.database.ArticleEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ArticleRepository @Inject constructor(
    val dao: ArticleDao
) {
    val articlesCached: Flow<List<ArticleEntity>> = dao.getArticles()

    suspend fun refreshNews() {
        delay(1000)
        val articles = getFromMock()
        dao.addArticles(articles)
    }

    private fun getFromMock(): List<ArticleEntity> {
        return listOf<ArticleEntity>(
            ArticleEntity("article1"),
            ArticleEntity("article2"),
            ArticleEntity("article3"),
            ArticleEntity("article4"),
            ArticleEntity("article5"),
            ArticleEntity("article6"),
        )
    }
}