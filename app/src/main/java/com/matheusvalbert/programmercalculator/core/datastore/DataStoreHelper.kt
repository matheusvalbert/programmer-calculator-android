package com.matheusvalbert.programmercalculator.core.datastore

interface DataStoreHelper {

  suspend fun setNumberOfInteractions(value: Int)

  suspend fun getNumberOfInteractions(): Int
}
