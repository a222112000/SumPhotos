package com.alselwi.sumimagesapi.di

import com.alselwi.sumimagesapi.data.ApiPictures
import com.alselwi.sumimagesapi.data.RepositoryImpl
import com.alselwi.sumimagesapi.domain.Repository
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
object AppModule {

    val BASE_URL = "https://picsum.photos/"

    @Singleton
    @Provides
    fun provideApiRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun picsService(): ApiPictures = provideApiRetrofit().create(ApiPictures::class.java)

    @Provides
    @Singleton
    fun providePhotoRepository(api: ApiPictures): Repository {
        return RepositoryImpl(api)
    }
}