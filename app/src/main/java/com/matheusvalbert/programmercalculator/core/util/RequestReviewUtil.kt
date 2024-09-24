package com.matheusvalbert.programmercalculator.core.util

import android.app.Activity
import android.os.Build
import com.google.android.play.core.review.ReviewManagerFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object RequestReviewUtil {
  fun requestReviewIfNeeded(
    activity: Activity, coroutineScope: CoroutineScope, shouldRequestReview: suspend () -> Boolean
  ) {
    coroutineScope.launch {
      if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        return@launch
      }
      if (!shouldRequestReview()) {
        return@launch
      }
      val reviewManager = ReviewManagerFactory.create(activity)
      reviewManager.requestReviewFlow().addOnCompleteListener {
        if (it.isSuccessful) {
          reviewManager.launchReviewFlow(activity, it.result)
        }
      }
    }
  }
}
