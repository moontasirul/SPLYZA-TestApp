package com.splyza.testapp.di


import com.splyza.testapp.data.repository.MainRepositoryImpl
import com.splyza.testapp.data.webservice.TeamService
import com.splyza.testapp.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        apiService: TeamService,
        dispatcher: CoroutineDispatcher
    ): MainRepository {
        return MainRepositoryImpl(apiService, dispatcher)
    }
}
