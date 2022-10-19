package com.dmitrienko.demoapp2.di

import com.dmitrienko.demoapp2.data.ranking.UserScoreRepositoryImpl
import com.dmitrienko.demoapp2.domain.score.repos.UserScoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(
        repo: UserScoreRepositoryImpl
    ): UserScoreRepository

}
