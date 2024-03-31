package com.matheusvalbert.programmercalculator.core.util

import android.app.Activity
import com.google.android.play.core.review.ReviewManagerFactory
import com.matheusvalbert.programmercalculator.core.CalculatorViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object RequestReviewUtil {
  fun requestReviewIfNeeded(
    activity: Activity, coroutineScope: CoroutineScope, calculatorViewModel: CalculatorViewModel
  ) {
    coroutineScope.launch {
      if (calculatorViewModel.shouldRequestReview()) {
        val reviewManager = ReviewManagerFactory.create(activity)
        reviewManager.requestReviewFlow().addOnCompleteListener {
          if (it.isSuccessful) {
            reviewManager.launchReviewFlow(activity, it.result)
          }
        }
      }
    }
  }
}
