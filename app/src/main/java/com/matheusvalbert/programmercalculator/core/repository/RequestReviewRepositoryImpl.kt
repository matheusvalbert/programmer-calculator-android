package com.matheusvalbert.programmercalculator.core.repository

import com.matheusvalbert.programmercalculator.core.datastore.DataStoreHelper
import javax.inject.Inject

class RequestReviewRepositoryImpl @Inject constructor(
  private val dataStoreHelper: DataStoreHelper
): RequestReviewRepository {

  override suspend fun updateAlreadyRequested(value: Boolean) = dataStoreHelper.updateBoolean(value)

  override suspend fun updateNumberOfInteractions(value: Int) = dataStoreHelper.updateInteger(value)

  override suspend fun hasBeenRequested() = dataStoreHelper.readBoolean()

  override suspend fun getNumberOfInteractions() = dataStoreHelper.readInteger()
}
