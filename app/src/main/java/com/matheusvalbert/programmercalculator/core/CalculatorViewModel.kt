package com.matheusvalbert.programmercalculator.core

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheusvalbert.programmercalculator.core.event.BaseEvent
import com.matheusvalbert.programmercalculator.core.event.InputEvent
import com.matheusvalbert.programmercalculator.core.usecase.CalculatorUseCases
import com.matheusvalbert.programmercalculator.core.util.CrashlyticsUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
  private val calculatorUseCases: CalculatorUseCases
) : ViewModel() {

  private val _result = mutableStateOf(ResultSate())
  val result: State<ResultSate> = _result
  private var onInputChanged: (() -> Unit)? = null

  fun onChangeBaseEvent(event: BaseEvent) {
    when (event) {
      is BaseEvent.Hex -> _result.value = result.value.copy(baseInput = BaseEvent.Hex)
      is BaseEvent.Dec -> _result.value = result.value.copy(baseInput = BaseEvent.Dec)
      is BaseEvent.Oct -> _result.value = result.value.copy(baseInput = BaseEvent.Oct)
      is BaseEvent.Bin -> _result.value = result.value.copy(baseInput = BaseEvent.Bin)
    }

    _result.value = calculatorUseCases.copyResultForInput(result.value)

    _result.value = result.value.copy(cursorPosition = result.value.input.length)
  }

  fun onInputEvent(event: InputEvent) {
    try {
      when (event) {
        is InputEvent.Keyboard -> _result.value = calculatorUseCases.keyboardInput(result.value, event.input)

        is InputEvent.ChangeInputPosition -> _result.value = calculatorUseCases.changeInputPosition(result.value, event.inputPosition)

        is InputEvent.Digit -> _result.value = calculatorUseCases.newDigitToExpression(result.value, event.digit)

        is InputEvent.Operation -> _result.value = calculatorUseCases.newOperationToExpression(result.value, event.operation)

        is InputEvent.Clear -> _result.value = calculatorUseCases.clearUseCase(result.value)

        is InputEvent.OpenParentheses -> _result.value = calculatorUseCases.openParentheses(result.value)

        is InputEvent.CloseParentheses -> _result.value = calculatorUseCases.closeParentheses(result.value)

        is InputEvent.Shl -> _result.value = calculatorUseCases.shiftLeftUseCase(result.value)

        is InputEvent.Shr -> _result.value = calculatorUseCases.shiftRightUseCase(result.value)

        is InputEvent.Delete -> _result.value = calculatorUseCases.deleteUseCase(result.value)

        is InputEvent.Equal -> _result.value = calculatorUseCases.equalUseCase(result.value)
      }

      onInputChanged?.let { it() }

      viewModelScope.launch {
        _result.value = calculatorUseCases.generateResultsBeforeInput(result.value)
      }

    } catch (e: Exception) {
      CrashlyticsUtil.dumpResultState(result.value, e)
    }
  }

  fun onInputChanged(callback: () -> Unit) {
    onInputChanged = callback
  }

  suspend fun shouldRequestReview(): Boolean {
    calculatorUseCases.updateNumberOfInteractionsUseCase()

    return calculatorUseCases.shouldRequestReviewUseCase()
  }
}
