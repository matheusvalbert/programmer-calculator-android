package com.matheusvalbert.programmercalculator.core.util

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.crashlytics.setCustomKeys
import com.google.firebase.ktx.Firebase
import com.matheusvalbert.programmercalculator.core.ResultSate

object CrashlyticsUtil {
  private const val HEXADECIMAL = "hexadecimal"
  private const val DECIMAL = "decimal"
  private const val OCTAL = "octal"
  private const val BINARY = "binary"
  private const val INPUT = "input"
  private const val INPUT_POSITION = "input_position"
  private const val CURSOR_POSITION = "cursor_position"
  private const val BASE_INPUT = "base_input"

  fun dumpResultState(result: ResultSate, exception: Exception) {
    val crashlytics = Firebase.crashlytics
    crashlytics.setCustomKeys {
      key(HEXADECIMAL, result.hex)
      key(DECIMAL, result.dec)
      key(OCTAL, result.oct)
      key(BINARY, result.bin)
      key(INPUT, result.input)
      key(INPUT_POSITION, result.inputPosition)
      key(CURSOR_POSITION, result.cursorPosition)
      key(BASE_INPUT, result.baseInput.javaClass.simpleName)
    }
    crashlytics.recordException(exception)
  }
}
