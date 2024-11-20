@file:OptIn(ExperimentalSerializationApi::class)

package com.example.moviestesttask.di

import com.example.moviestesttask.api.TmdbApiService
import com.example.moviestesttask.misc.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	private const val BASE_URL = "https://api.themoviedb.org/3/"

	@Singleton
	@Provides
	fun provideHttpClient(): OkHttpClient {
		val logging = HttpLoggingInterceptor()
		logging.setLevel(HttpLoggingInterceptor.Level.BODY)
		return OkHttpClient
			.Builder()
			.addInterceptor(logging)
			.readTimeout(60, TimeUnit.SECONDS)
			.connectTimeout(60, TimeUnit.SECONDS)
			.build()
	}

	@Singleton
	@Provides
	fun provideRetrofit(
		okHttpClient: OkHttpClient,
	): Retrofit {
		return Retrofit.Builder()
			.baseUrl(BASE_URL)
			.client(okHttpClient)
			.addConverterFactory(json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
			.build()
	}

	@Singleton
	@Provides
	fun provideService(retrofit: Retrofit): TmdbApiService =
		retrofit.create(TmdbApiService::class.java)

	private val json by lazy {
		Json {
			encodeDefaults = false
			ignoreUnknownKeys = true
			isLenient = true
			allowSpecialFloatingPointValues = false
			allowStructuredMapKeys = true
			prettyPrint = false
			coerceInputValues = true
			useArrayPolymorphism = false
		}
	}
}
