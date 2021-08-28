package com.example.core.core.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.core.core.db.CityWeatherDatabase
import com.example.core.util.AppSchedulerProvider
import com.example.core.util.SchedulerProvider
import com.example.core.util.data.APIConst.Companion.BASE_URL
import com.example.core.util.data.DbConst
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }


    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @Singleton
    fun provideTutorialsHubDatabase( context: Context): CityWeatherDatabase {
        return Room.databaseBuilder(context, CityWeatherDatabase::class.java, DbConst.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    internal fun provideHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(15, TimeUnit.SECONDS)
        httpClient.readTimeout(15, TimeUnit.SECONDS)
        httpClient.writeTimeout(15, TimeUnit.SECONDS)
        httpClient.retryOnConnectionFailure(true)

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(logging)

        return httpClient.build()
    }


    @Singleton
    @Provides
    fun provideRetrofitBuilder(httpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())

    }


}