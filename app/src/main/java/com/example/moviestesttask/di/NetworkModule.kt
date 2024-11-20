package com.example.moviestesttask.di

import com.example.moviestesttask.BuildConfig
import com.example.moviestesttask.api.TmdbApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	@Singleton
	@Provides
	fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor, requestInterceptor: Interceptor): OkHttpClient {
		return OkHttpClient
			.Builder()
			.addInterceptor(loggingInterceptor)
			.addInterceptor(requestInterceptor)
			.readTimeout(60, TimeUnit.SECONDS)
			.connectTimeout(60, TimeUnit.SECONDS)
			.build()
	}

	@Provides
	internal fun loggingInterceptor(): HttpLoggingInterceptor {
		return HttpLoggingInterceptor().apply {
			setLevel(HttpLoggingInterceptor.Level.BODY)
		}
	}

	@Provides
	fun provideRequestInterceptor() = Interceptor {
		val builder = it.request().newBuilder().url(it.request().url)
		builder.header("Authorization", "Bearer ${BuildConfig.MOVIE_TOKEN}")
		it.proceed(builder.build())
	}

	@Singleton
	@Provides
	fun provideRetrofit(
		okHttpClient: OkHttpClient,
	): Retrofit {
		return Retrofit.Builder()
			.baseUrl(BuildConfig.ENDPOINT)
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
