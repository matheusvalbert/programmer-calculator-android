package com.matheusvalbert.programmercalculator.core.util

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.crashlytics.setCustomKeys
import com.google.firebase.ktx.Firebase
import com.matheusvalbert.programmercalculator.core.ResultSate
import com.matheusvalbert.programmercalculator.core.Base
import com.matheusvalbert.programmercalculator.core.Event

object CrashlyticsUtil {
  private const val RESULT_STATE = "result_state"
  private const val INPUT_EVENT = "input_event"
  private const val BASE_EVENT = "base_event"

  fun dumpResultState(
    result: ResultSate,
    event: Event? = null,
    base: Base? = null,
    exception: Exception
  ) {
    val crashlytics = Firebase.crashlytics
    crashlytics.setCustomKeys {
      key(RESULT_STATE, result.toString())
      if (event != null) {
        key(INPUT_EVENT, event.toString())
      }
      if (base != null) {
        key(BASE_EVENT, base.toString())
      }
    }
    crashlytics.recordException(exception)
  }
}
