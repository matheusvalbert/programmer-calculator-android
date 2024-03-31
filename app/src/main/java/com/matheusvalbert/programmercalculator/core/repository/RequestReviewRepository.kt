package com.matheusvalbert.programmercalculator.core.repository

interface RequestReviewRepository {

  suspend fun updateAlreadyRequested(value: Boolean)

  suspend fun updateNumberOfInteractions(value: Int)

  suspend fun hasBeenRequested(): Boolean

  suspend fun getNumberOfInteractions(): Int
}
