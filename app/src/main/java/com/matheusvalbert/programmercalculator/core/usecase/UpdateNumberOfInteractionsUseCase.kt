package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.datastore.DataStoreHelper
import javax.inject.Inject

class UpdateNumberOfInteractionsUseCase @Inject constructor(
  private val dataStoreHelperImpl: DataStoreHelper
) {

  suspend operator fun invoke() {
    var numberOfInteractions = dataStoreHelperImpl.getNumberOfInteractions()

    numberOfInteractions += 1

    if (numberOfInteractions == 50) {
      numberOfInteractions = 0
    }

    dataStoreHelperImpl.setNumberOfInteractions(numberOfInteractions)
  }
}
