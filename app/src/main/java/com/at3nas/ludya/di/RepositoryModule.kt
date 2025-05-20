package com.at3nas.ludya.di

import com.at3nas.ludya.data.repository.AuthRepositoryImpl
import com.at3nas.ludya.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl : AuthRepositoryImpl
    ): AuthRepository
}