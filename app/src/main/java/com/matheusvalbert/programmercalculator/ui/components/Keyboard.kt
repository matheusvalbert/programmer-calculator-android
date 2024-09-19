package com.matheusvalbert.programmercalculator.ui.components

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.matheusvalbert.programmercalculator.core.CalculatorViewModel
import com.matheusvalbert.programmercalculator.core.event.BaseEvent
import com.matheusvalbert.programmercalculator.core.event.InputEvent
import com.matheusvalbert.programmercalculator.core.util.RequestReviewUtil

@Composable
fun Keyboard(
  calculatorViewModel: CalculatorViewModel = viewModel()
) {
  val activity = LocalContext.current as Activity
  val coroutineScope = rememberCoroutineScope()

  val height = ((LocalConfiguration.current.screenHeightDp / 7) * 0.63).toInt()
  val width = ((LocalConfiguration.current.screenWidthDp / 4) * 0.95).toInt()
  val functionButtonColor = MaterialTheme.colorScheme.secondary
  val operationButtonColor = MaterialTheme.colorScheme.surfaceVariant
  val operationButtonTextColor = Color.White

  Column(verticalArrangement = Arrangement.SpaceBetween) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
      Button(
        symbol = "C",
        height = height,
        width = width,
        backgroundColor = functionButtonColor,
        onClick = {
          calculatorViewModel.onInputEvent(InputEvent.Clear)
          RequestReviewUtil.requestReviewIfNeeded(activity, coroutineScope, calculatorViewModel)
        })
      Button(
        symbol = "(",
        height = height,
        width = width,
        backgroundColor = functionButtonColor,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.OpenParentheses) })
      Button(
        symbol = ")",
        height = height,
        width = width,
        backgroundColor = functionButtonColor,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.CloseParentheses) })
      Button(
        symbol = "<<",
        height = height,
        width = width,
        backgroundColor = functionButtonColor,
        enabled = calculatorViewModel.result.value.baseInput == BaseEvent.Bin,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Shl) })
    }
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
      Button(
        symbol = "D",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.baseInput == BaseEvent.Hex,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Digit("D")) })
      Button(
        symbol = "E",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.baseInput == BaseEvent.Hex,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Digit("E")) })
      Button(
        symbol = "F",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.baseInput == BaseEvent.Hex,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Digit("F")) })
      Button(
        symbol = ">>",
        height = height,
        width = width,
        backgroundColor = functionButtonColor,
        enabled = calculatorViewModel.result.value.baseInput == BaseEvent.Bin,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Shr) })
    }
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
      Button(
        symbol = "A",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.baseInput == BaseEvent.Hex,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Digit("A")) })
      Button(
        symbol = "B",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.baseInput == BaseEvent.Hex,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Digit("B")) })
      Button(
        symbol = "C",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.baseInput == BaseEvent.Hex,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Digit("C")) })
      Button(
        symbol = "÷",
        height = height,
        width = width,
        backgroundColor = operationButtonColor,
        textColor = operationButtonTextColor,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Operation("÷")) })
    }
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
      Button(
        symbol = "7",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.baseInput != BaseEvent.Bin,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Digit("7")) })
      Button(
        symbol = "8",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.baseInput == BaseEvent.Hex || calculatorViewModel.result.value.baseInput == BaseEvent.Dec,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Digit("8")) })
      Button(
        symbol = "9",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.baseInput == BaseEvent.Hex || calculatorViewModel.result.value.baseInput == BaseEvent.Dec,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Digit("9")) })
      Button(
        symbol = "×",
        height = height,
        width = width,
        backgroundColor = operationButtonColor,
        textColor = operationButtonTextColor,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Operation("×")) }
      )
    }
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
      Button(
        symbol = "4",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.baseInput != BaseEvent.Bin,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Digit("4")) })
      Button(
        symbol = "5",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.baseInput != BaseEvent.Bin,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Digit("5")) })
      Button(
        symbol = "6",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.baseInput != BaseEvent.Bin,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Digit("6")) })
      Button(
        symbol = "-",
        height = height,
        width = width,
        backgroundColor = operationButtonColor,
        textColor = operationButtonTextColor,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Operation("-")) }
      )
    }
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
      Button(
        symbol = "1",
        height = height,
        width = width,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Digit("1")) })
      Button(
        symbol = "2",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.baseInput != BaseEvent.Bin,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Digit("2")) })
      Button(
        symbol = "3",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.baseInput != BaseEvent.Bin,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Digit("3")) })
      Button(
        symbol = "+",
        height = height,
        width = width,
        backgroundColor = operationButtonColor,
        textColor = operationButtonTextColor,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Operation("+")) }
      )
    }
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
      Button(
        symbol = "0",
        height = height,
        width = width * 2 + 8,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Digit("0")) })
      Button(
        symbol = "⌫",
        height = height,
        width = width,
        onClick = { calculatorViewModel.onInputEvent(InputEvent.Delete) })
      Button(
        symbol = "=",
        height = height,
        width = width,
        backgroundColor = operationButtonColor,
        textColor = operationButtonTextColor,
        onClick = {
          calculatorViewModel.onInputEvent(InputEvent.Equal)
          RequestReviewUtil.requestReviewIfNeeded(activity, coroutineScope, calculatorViewModel)
        }
      )
    }
  }
}

@Preview
@Composable
fun KeyboardPreview() {
  Keyboard()
}
