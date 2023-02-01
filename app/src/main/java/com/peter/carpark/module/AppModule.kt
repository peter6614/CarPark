package com.peter.carpark.module

import com.peter.carpark.api.CarParkService
import com.peter.carpark.api.LoginService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val LoginURL = "https://noodoe-app-development.web.app"
    private const val CarParkURL = "https://tcgbusfs.blob.core.windows.net/blobtcmsv/"
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class LoginRetrofit
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CarParkRetrofit

    @Provides
    fun provideLoginApiService(@LoginRetrofit retrofit: Retrofit): LoginService = retrofit.create(LoginService::class.java)
    @Provides
    fun provideCarParkApiService(@CarParkRetrofit retrofit: Retrofit): CarParkService = retrofit.create(CarParkService::class.java)

    @Singleton
    @LoginRetrofit
    @Provides
    fun provideLoginRetrofit (okHttp: OkHttpClient) : Retrofit  {
        return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(LoginURL)
            .client(okHttp)
            .build()
    }

    @Singleton
    @CarParkRetrofit
    @Provides
    fun provideCarParkRetrofit (okHttp: OkHttpClient) : Retrofit  {
        return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(CarParkURL)
            .client(okHttp)
            .build()
    }
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30000, TimeUnit.SECONDS)
            .connectTimeout(30000, TimeUnit.SECONDS).build()
    }


}