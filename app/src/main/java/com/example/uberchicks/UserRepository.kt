package com.example.uberchicks

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val dataStore: DataStore<Preferences>) {


    //Exposing the user as Flow
    val userPreferenceFlow: Flow<UserPreference> = dataStore.data
        .map { preferences->
            val userId = preferences[UserPreferenceKeys.USER_ID]?:""
            val userEmail = preferences[UserPreferenceKeys.USER_EMAIL]?:""
            val firstName = preferences[UserPreferenceKeys.FIRST_NAME]?:""
            val lastName = preferences[UserPreferenceKeys.LAST_NAME]?:""
            val token = preferences[UserPreferenceKeys.TOKEN]?:""
            val isLoggedIn = preferences[UserPreferenceKeys.IS_LOGGED_IN]?:false

            UserPreference(userId,firstName,lastName,userEmail,token, isLoggedIn)
        }

    val isLoggedIn:Flow<Boolean> = userPreferenceFlow.map{
        it.isLoggedIn
    }

    //TODO Adding the user to preference(Pass an object to this function)
    suspend fun addUser() {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[UserPreferenceKeys.USER_ID] = "0"
            mutablePreferences[UserPreferenceKeys.USER_EMAIL] = "anthonyangatia@gmail.com"
            mutablePreferences[UserPreferenceKeys.TOKEN] = "token"
        }
    }


    suspend fun deleteUserPreference(){
        dataStore.edit { mutablePreferences ->
            mutablePreferences.remove(UserPreferenceKeys.USER_ID)
            mutablePreferences.remove(UserPreferenceKeys.USER_EMAIL)
            mutablePreferences.remove(UserPreferenceKeys.FIRST_NAME)
            mutablePreferences.remove(UserPreferenceKeys.LAST_NAME)
            mutablePreferences.remove(UserPreferenceKeys.TOKEN)
            mutablePreferences.remove(UserPreferenceKeys.IS_LOGGED_IN)
        }
    }
}