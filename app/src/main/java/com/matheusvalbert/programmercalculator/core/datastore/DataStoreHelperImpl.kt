package com.matheusvalbert.programmercalculator.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DataStoreHelperImpl @Inject constructor(@ApplicationContext val context: Context): DataStoreHelper {

  private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_DATA_STORE)

  companion object {
    private const val PREFERENCES_DATA_STORE = "preferencesDataStore"
    private val NUMBER_OF_INTERACTIONS = intPreferencesKey("numberOfInteractions")
  }

  override suspend fun setNumberOfInteractions(value: Int) {
    context.dataStore.edit { settings ->
      settings[NUMBER_OF_INTERACTIONS] = value
    }
  }

  override suspend fun getNumberOfInteractions(): Int {
    val preferences = context.dataStore.data.first()
    return preferences[NUMBER_OF_INTERACTIONS] ?: 0
  }
}
