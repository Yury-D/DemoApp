package com.dmitrienko.demoapp2.di

import android.content.Context
import androidx.room.Room
import com.dmitrienko.demoapp2.data.ranking.room.AppDatabase
import com.dmitrienko.demoapp2.data.ranking.room.DATABASE_NAME
import com.dmitrienko.demoapp2.data.ranking.room.GamesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
class DatabaseModule {
    @Provides
    fun provideGamesDao(appDatabase: AppDatabase): GamesDao {
        return appDatabase.gamesDao()
    }

    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}