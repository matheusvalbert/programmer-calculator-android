package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.datastore.DataStoreHelper
import javax.inject.Inject

class ShouldRequestReviewUseCase @Inject constructor(
  private val dataStoreHelperImpl: DataStoreHelper
) {
  suspend operator fun invoke(): Boolean {
    val numberOfInteractions = dataStoreHelperImpl.getNumberOfInteractions()

    return numberOfInteractions == 15
  }
}
