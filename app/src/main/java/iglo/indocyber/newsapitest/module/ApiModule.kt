package iglo.indocyber.newsapitest.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import iglo.indocyber.api_service.RetrofitClient
import iglo.indocyber.api_service.service.EverythingService
import iglo.indocyber.api_service.service.SourceService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Singleton
    fun provideRetrofit() = RetrofitClient.getClient()

    @Provides
    @Singleton
    fun provideSourceService(retrofit: Retrofit) = retrofit.create(SourceService::class.java)

    @Provides
    @Singleton
    fun provideEverythingService(retrofit: Retrofit) = retrofit.create(EverythingService::class.java)
}