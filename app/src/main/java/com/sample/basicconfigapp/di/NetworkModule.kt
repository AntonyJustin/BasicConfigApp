package com.sample.basicconfigapp.di

import com.sample.basicconfigapp.api.CountriesApi
import com.sample.basicconfigapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {

        val customHeaderInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val modifiedRequest = originalRequest.newBuilder()
//                .header(
//                    "Authorization",
//                    HEADER
//                )
                .build()
            chain.proceed(modifiedRequest)
        }

        // Create an instance of the OkHttp Interceptor for logging
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                // Add print statements here for the URL and response message
                println(message)
            }
        })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        // Build the OkHttpClient with the interceptor
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(customHeaderInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient) // Use the custom OkHttpClient with the interceptor
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesCountriesListApi(retrofit: Retrofit): CountriesApi {
        return retrofit.create(CountriesApi::class.java)
    }


}