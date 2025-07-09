package com.at3nas.ludya.di

import com.at3nas.ludya.data.repository.AccountDeletionRepositoryImpl
import com.at3nas.ludya.data.repository.AuthRepositoryImpl
import com.at3nas.ludya.data.repository.CourseRepositoryImpl
import com.at3nas.ludya.data.repository.ProfileRepositoryImpl
import com.at3nas.ludya.data.repository.UserRepositoryImpl
import com.at3nas.ludya.domain.repository.AccountDeletionRepository
import com.at3nas.ludya.domain.repository.AuthRepository
import com.at3nas.ludya.domain.repository.CourseRepository
import com.at3nas.ludya.domain.repository.ProfileRepository
import com.at3nas.ludya.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun bindProfileRepository(
        profileRepositoryImpl: ProfileRepositoryImpl
    ): ProfileRepository

    @Binds
    abstract fun bindCourseRepository(
        courseRepositoryImpl: CourseRepositoryImpl
    ): CourseRepository

    @Binds
    abstract fun bindAccountDeletionRepository(
        accountDeletionRepositoryImpl: AccountDeletionRepositoryImpl
    ): AccountDeletionRepository
}