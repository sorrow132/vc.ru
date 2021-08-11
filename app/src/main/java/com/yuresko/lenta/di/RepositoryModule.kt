package com.yuresko.lenta.di

import com.yuresko.lenta.data.Repository
import com.yuresko.lenta.data.RepositoryImpl
import com.yuresko.lenta.data.TjournalApi
import com.yuresko.lenta.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideRepositoryRepo(
        service: TjournalApi,
        dispatcherProvider: DispatcherProvider
    ): Repository {
        return RepositoryImpl(service, dispatcherProvider)
    }

    @Provides
    @ViewModelScoped
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }
}