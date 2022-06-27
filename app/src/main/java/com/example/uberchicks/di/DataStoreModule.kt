package com.example.uberchicks.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.uberchicks.CART_PREFERENCES
import com.example.uberchicks.USER_PREFERENCES
import com.example.uberchicks.UserPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    /**
     *
     * */
    @Singleton
    @Provides
    fun providePreferenceDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { appContext.preferencesDataStoreFile(USER_PREFERENCES) }
        )

    }

//    @Singleton
//    @Provides
//    fun provideUserPreferenceDataStore(@ApplicationContext appContext: Context): DataStore<Preferences>{
//        return PreferenceDataStoreFactory.create(
//            produceFile = {appContext.preferencesDataStoreFile(USER_PREFERENCES)}
//        )
//    }
}