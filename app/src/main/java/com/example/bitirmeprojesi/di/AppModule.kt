package com.example.bitirmeprojesi.di

import com.example.bitirmeprojesi.data.repo.YemeklerDaoRepository
import com.example.bitirmeprojesi.retrofit.ApiUtils
import com.example.bitirmeprojesi.retrofit.YemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideYemeklerDaoRepository(yemekdao:YemeklerDao): YemeklerDaoRepository{
        return YemeklerDaoRepository(yemekdao)
    }

    @Provides
    @Singleton
    fun provideYemeklerDao() : YemeklerDao{
        return ApiUtils.getYemeklerDao()
    }
}