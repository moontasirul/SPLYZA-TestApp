package com.splyza.testapp.di.uiModule

import androidx.fragment.app.FragmentFactory
import com.splyza.testapp.presentation.home.AppFragmentFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object FragmentModule {
    @Singleton
    @Provides
    fun provideMainFragmentFactory(): FragmentFactory {
        return AppFragmentFactory()
    }
}