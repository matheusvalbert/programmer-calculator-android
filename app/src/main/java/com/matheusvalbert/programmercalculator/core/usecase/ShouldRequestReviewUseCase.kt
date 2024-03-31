package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.repository.RequestReviewRepository
import javax.inject.Inject

class ShouldRequestReviewUseCase @Inject constructor(
  private val requestReviewRepository: RequestReviewRepository
) {
  suspend operator fun invoke(): Boolean {
    val numberOfInteractions = requestReviewRepository.getNumberOfInteractions()

    if(numberOfInteractions == 15) {
      requestReviewRepository.updateAlreadyRequested(true)
      return true
    }

    return false
  }
}
