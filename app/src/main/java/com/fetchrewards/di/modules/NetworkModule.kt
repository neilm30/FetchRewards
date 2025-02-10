package com.fetchrewards.di.modules

import com.fetchrewards.constants.Constants
import com.fetchrewards.data.remote.apis.FetchRewardsApis
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    val modules =
        module {
            single { provideHttpClient() }
            single { provideRetrofit(get()) }
            single { provideService(get()) }
        }

    private fun provideHttpClient(loggingEnabled: Boolean = true): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (loggingEnabled) {
            builder.addInterceptor(provideHttpLoggingInterceptor())
        }

        return builder
            .readTimeout(Constants.NETWORK_READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(Constants.NETWORK_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(provideConverterFactory())) // Use Moshi converter
            .build()
    }

    private fun provideConverterFactory(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory()) // Add support for Kotlin data classes
            .build()

    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return httpLoggingInterceptor
    }

    private fun provideService(retrofit: Retrofit): FetchRewardsApis = retrofit.create(FetchRewardsApis::class.java)
}
