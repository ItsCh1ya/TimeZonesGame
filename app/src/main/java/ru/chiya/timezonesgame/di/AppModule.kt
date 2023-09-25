package ru.chiya.timezonesgame.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.chiya.data.repository.TimezonesRepositoryImpl
import ru.chiya.domain.repisotory.TimezonesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRepository(): TimezonesRepository {
        return TimezonesRepositoryImpl()
    }
}