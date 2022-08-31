package com.splyza.testapp.di


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.splyza.testapp.BuildConfig
import com.splyza.testapp.core.network.interceptor.AuthInterceptor
import com.splyza.testapp.core.network.interceptor.HttpRequestInterceptor
import com.splyza.testapp.data.webservice.TeamService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpRequestInterceptor())
            .addInterceptor(AuthInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    //.addConverterFactory(MoshiConverterFactory.create())
    @Provides
    @Singleton
    fun provideTeamService(retrofit: Retrofit): TeamService {
        return retrofit.create(TeamService::class.java)
    }
}
