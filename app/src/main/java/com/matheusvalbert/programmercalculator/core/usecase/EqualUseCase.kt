package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate
import com.matheusvalbert.programmercalculator.core.event.BaseEvent

class EqualUseCase {

  operator fun invoke(state: ResultSate): ResultSate {
    return when (state.baseInput) {
      is BaseEvent.Hex -> state.copy(input = state.hex, cursorPosition = state.input.length)
      is BaseEvent.Dec -> state.copy(input = state.dec, cursorPosition = state.input.length)
      is BaseEvent.Oct -> state.copy(input = state.oct, cursorPosition = state.input.length)
      is BaseEvent.Bin -> state.copy(input = state.bin, cursorPosition = state.input.length)
    }
  }
}
