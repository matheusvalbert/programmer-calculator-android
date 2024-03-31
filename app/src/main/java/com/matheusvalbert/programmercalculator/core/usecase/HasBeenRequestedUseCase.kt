package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.repository.RequestReviewRepository
import javax.inject.Inject

class HasBeenRequestedUseCase @Inject constructor(
  private val requestReviewRepository: RequestReviewRepository
) {
  suspend operator fun invoke() = requestReviewRepository.hasBeenRequested()
}
