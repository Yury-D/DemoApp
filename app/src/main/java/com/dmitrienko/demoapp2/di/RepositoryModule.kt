package com.dmitrienko.demoapp2.di

import com.dmitrienko.demoapp2.data.ranking.GamesRepositoryImpl
import com.dmitrienko.demoapp2.domain.score.repos.GamesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(
        repo: GamesRepositoryImpl
    ): GamesRepository
}

