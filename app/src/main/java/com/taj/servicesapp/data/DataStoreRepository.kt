package com.taj.servicesapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.taj.servicesapp.model.auth.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "on_boarding_pref")

class DataStoreRepository(context: Context) {

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = "on_boarding_completed")
        val userDataKey = stringPreferencesKey(name = "user_data")

    }

    private val dataStore = context.dataStore

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }

    suspend fun saveUser(user: UserData) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.userDataKey] = Gson().toJson(user)
        }
    }

    fun readUser(): Flow<UserData?> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val userDataJson = preferences[PreferencesKey.userDataKey]
                if (userDataJson != null) {
                    Gson().fromJson(userDataJson, UserData::class.java)
                } else {
                    null
                }
            }
    }

    fun readLoginStatus(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val userDataJson = preferences[PreferencesKey.userDataKey]
                val userData = Gson().fromJson(userDataJson, UserData::class.java)
                userData?.token != null
            }
    }

    fun readToken(): Flow<String?> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val userDataJson = preferences[PreferencesKey.userDataKey]
                val userData = Gson().fromJson(userDataJson, UserData::class.java)
                "Bearer "+ userData?.token
            }
    }
    suspend fun clearUserData() {
        dataStore.edit { preferences ->
            preferences.remove(PreferencesKey.userDataKey)
        }
    }


}