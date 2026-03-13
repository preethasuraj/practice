package com.example.newsarticle.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface ArticleDao {
    @Insert(entity = ArticleEntity::class, onConflict = REPLACE)
    suspend fun addArticles(articles: List<ArticleEntity>)

    @Query("Select * From Article" )
    fun getArticles(): Flow<List<ArticleEntity>>

}