package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate
import com.matheusvalbert.programmercalculator.core.util.dropValueAfterCursor

class DeleteUseCase {

  operator fun invoke(state: ResultSate): ResultSate {
    return if (state.input.isBlank() || state.input.count() == 1) {
      state.copy(input = "")
    } else {
      state.copy(
        input = state.input.dropValueAfterCursor(state.inputPosition),
        cursorPosition = state.cursorPosition - 1
      )
    }
  }
}
