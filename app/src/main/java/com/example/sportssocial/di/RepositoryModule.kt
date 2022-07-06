package com.example.sportssocial.di

import android.content.Context
import com.example.sportssocial.data.repo.AthleteRepository
import com.example.sportssocial.data.repo.FirestoreRepo
import com.example.sportssocial.data.repo.NewsArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

        @Provides
        @ViewModelScoped
        fun provideMainRepository (
                @ApplicationContext appContext : Context
        ) : AthleteRepository {
                return AthleteRepository(appContext)
        }

        @Provides
        @ViewModelScoped
        fun provideFirestoreRepo() : FirestoreRepo {
                return FirestoreRepo()
        }

        @Provides
        @ViewModelScoped
        fun provideNewsArticleRepository(
                @ApplicationContext appContext : Context
        )
        : NewsArticleRepository {
                return NewsArticleRepository(appContext)
        }

}