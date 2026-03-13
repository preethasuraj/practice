package com.example.practice2.database

import android.content.Context
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import androidx.room.RoomDatabaseConstructor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDao(@ApplicationContext context: Context): EmployeeDao {
        return Room.databaseBuilder(
            context = context,
            klass = EmployeeDatabase::class.java,
            name = "Employee Database"
        )
            .fallbackToDestructiveMigration(false)
            .build().employeeDao()
    }

}