package com.dmitrienko.demoapp2.di

import com.dmitrienko.demoapp2.domain.score.interactors.GetRankingsInteractor
import com.dmitrienko.demoapp2.domain.score.repos.GamesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class InteractorModule {
    @Provides
    fun provideRankingInteractor(repository: GamesRepository): GetRankingsInteractor {
        return GetRankingsInteractor(repository)
    }
}