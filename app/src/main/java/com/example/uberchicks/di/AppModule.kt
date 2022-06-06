package com.example.uberchicks.di

import android.content.Context
import androidx.room.Room
import com.example.uberchicks.database.CartDao
import com.example.uberchicks.database.UberChicksDatabase
import com.example.uberchicks.network.UberChicksApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //We want only one instance of thos object and we want it to be accessib
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun providesRetrofit(moshi: Moshi) = Retrofit.Builder()
        .baseUrl(UberChicksApiService.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()


    @Provides
    @Singleton
    fun provideUberChicksApiService(retrofit: Retrofit) =
        retrofit.create(UberChicksApiService::class.java)


    @Provides
    @Singleton
    fun provideCartDao(uberChicksDatabase: UberChicksDatabase): CartDao {
        return uberChicksDatabase.cartDao()

    }

    @Singleton
    @Provides
    fun provideTaskDatabase(@ApplicationContext context: Context, callback: UberChicksDatabase.Callback): UberChicksDatabase{
        return Room.databaseBuilder(context,UberChicksDatabase::class.java, "uber_chicks_database")
            .addCallback(callback)
            .build()
    }

}