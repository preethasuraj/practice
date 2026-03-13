package com.example.newsarticle.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "Article")
data class ArticleEntity (
    @PrimaryKey
    val url: String
)