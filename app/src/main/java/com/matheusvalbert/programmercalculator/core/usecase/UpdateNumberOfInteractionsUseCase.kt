package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.repository.RequestReviewRepository
import javax.inject.Inject

class UpdateNumberOfInteractionsUseCase @Inject constructor(
  private val requestReviewRepository: RequestReviewRepository
) {
  suspend operator fun invoke() {
    var numberOfInteractions = requestReviewRepository.getNumberOfInteractions()

    numberOfInteractions += 1

    requestReviewRepository.updateNumberOfInteractions(numberOfInteractions)
  }
}
