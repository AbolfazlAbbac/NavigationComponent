package com.example.project.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.project.utils.Utils.USERS_DATA_STORE
import com.example.project.utils.Utils.USERS_DATA_STORE_KEY
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreUsers @Inject constructor(@ApplicationContext val context: Context) {
    companion object {
        private val Context.dataStoreUsers: DataStore<Preferences> by preferencesDataStore(
            USERS_DATA_STORE
        )
        private val stringPreferencesKey = stringPreferencesKey(USERS_DATA_STORE_KEY)
    }

    suspend fun saveTokenDataStore(token: String) {
        context.dataStoreUsers.edit {
            it[stringPreferencesKey] = token
        }
    }

    fun readDataStore() = context.dataStoreUsers.data.map {
        it[stringPreferencesKey] ?: ""
    }
}

