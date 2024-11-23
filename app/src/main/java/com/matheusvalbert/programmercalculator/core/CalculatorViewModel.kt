package com.matheusvalbert.programmercalculator.core

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

  fun onEvent(event: Event) {
    try {
      _result.value = when (event) {
        is Event.ChangeBase -> calculatorUseCases.changeBaseEvent(result.value, event.base)

        is Event.Keyboard -> calculatorUseCases.keyboardInput(result.value, event.input)

        is Event.ChangePosition -> calculatorUseCases.changeInputPosition(result.value, event.inputPosition)

        is Event.Digit -> calculatorUseCases.newDigitToExpression(result.value, event.digit)

        is Event.Operation -> calculatorUseCases.newOperationToExpression(result.value, event.operation)

        is Event.Clear -> calculatorUseCases.clearUseCase(result.value)

        is Event.OpenParentheses -> calculatorUseCases.openParentheses(result.value)

        is Event.CloseParentheses -> calculatorUseCases.closeParentheses(result.value)

        is Event.Shl -> calculatorUseCases.shiftLeftUseCase(result.value)

        is Event.Shr -> calculatorUseCases.shiftRightUseCase(result.value)

        is Event.Delete -> calculatorUseCases.deleteUseCase(result.value)

        is Event.Equal -> calculatorUseCases.equalUseCase(result.value)
      }

      onInputChanged?.let { it() }

      viewModelScope.launch {
        _result.value = calculatorUseCases.generateResultsBeforeInput(result.value)
      }
    } catch (e: Exception) {
      CrashlyticsUtil.dumpResultState(result = result.value, event = event, exception = e)
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
