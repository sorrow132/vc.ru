package com.yuresko.lenta.di

import com.yuresko.lenta.data.TjournalApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @Provides
    @Singleton
    fun provideServiceUser(retrofit: Retrofit): TjournalApi {
        return retrofit.create(TjournalApi::class.java)
    }
}