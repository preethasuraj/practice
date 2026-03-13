package com.example.newsarticle.di

import android.content.Context
import androidx.room.Room
import com.example.newsarticle.database.ArticleDao
import com.example.newsarticle.database.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDao(@ApplicationContext context: Context): ArticleDao {
       return Room.databaseBuilder<ArticleDatabase>(context, "ArticleDatabase::class.nestedClasses")
            .build()
           .articleDao()
    }
}