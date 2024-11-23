package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate
import com.matheusvalbert.programmercalculator.core.Base

class ChangeBaseEvent {
  operator fun invoke(state: ResultSate, newBase: Base): ResultSate {
    val result = if (state.hex == "Overflow") ResultSate(base = newBase) else
      when (newBase) {
        is Base.Hex -> state.copy(base = newBase, input = state.hex, inputPosition = state.hex.length, cursorPosition = state.hex.length)
        is Base.Dec -> state.copy(base = newBase, input = state.dec, inputPosition = state.dec.length, cursorPosition = state.dec.length)
        is Base.Oct -> state.copy(base = newBase, input = state.oct, inputPosition = state.oct.length, cursorPosition = state.oct.length)
        is Base.Bin -> state.copy(base = newBase, input = state.bin, inputPosition = state.bin.length, cursorPosition = state.bin.length)
      }
    if (result.input == "0") return result.copy(input = "")
    return result
  }
}
