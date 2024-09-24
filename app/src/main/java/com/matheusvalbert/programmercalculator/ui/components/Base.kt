package com.matheusvalbert.programmercalculator.ui.components

import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.matheusvalbert.programmercalculator.core.CalculatorViewModel
import com.matheusvalbert.programmercalculator.core.event.BaseEvent
import com.matheusvalbert.programmercalculator.ui.theme.ProgrammerCalculatorTheme

@Composable
fun Base(
  name: String,
  result: String,
  active: Boolean,
  shouldRequestReview: () -> Unit,
  modifier: Modifier = Modifier,
  height: Int = (LocalConfiguration.current.screenHeightDp * 0.05).toInt(),
  calculatorViewModel: CalculatorViewModel = viewModel()
) {
  val context = LocalContext.current
  val clipboardManager: ClipboardManager = LocalClipboardManager.current

  val textColor = MaterialTheme.colorScheme.primary

  var boxHeight by remember {
    mutableIntStateOf(0)
  }

  fun copyResultToClipboard() {
    val resultToClipboard = when (name) {
      "HEX" -> calculatorViewModel.result.value.hex
      "DEC" -> calculatorViewModel.result.value.dec
      "OCT" -> calculatorViewModel.result.value.oct
      "BIN" -> calculatorViewModel.result.value.bin
      else -> calculatorViewModel.result.value.hex
    }
    clipboardManager.setText(AnnotatedString(resultToClipboard))
    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2) {
      Toast.makeText(context, "$name result copied to clipboard.", Toast.LENGTH_SHORT).show()
    }
  }

  Box(Modifier.pointerInput(Unit) {
    detectTapGestures(
      onTap = {
        calculatorViewModel.onChangeBaseEvent(
          when (name) {
            "HEX" -> BaseEvent.Hex
            "DEC" -> BaseEvent.Dec
            "OCT" -> BaseEvent.Oct
            "BIN" -> BaseEvent.Bin
            else -> BaseEvent.Hex
          }
        )
        shouldRequestReview()
      },
      onLongPress = { copyResultToClipboard() }
    )
  }) {
    Row(modifier = modifier
        .height(height.dp)
        .onGloballyPositioned { boxHeight = it.size.height }) {
      Spacer(
        modifier = modifier
            .height(boxHeight.dp)
            .width(8.dp)
            .background(if (active) MaterialTheme.colorScheme.surfaceVariant else Color.Unspecified)
      )
      Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp), horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Column(
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier
            .height(height.dp)
        ) {
          AutoResizedText(
            text = name,
            textColor = textColor,
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier
          )
        }
        Column(
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier
            .height(height.dp)
        ) {
          AutoResizedText(
            text = result,
            textColor = textColor,
            style = MaterialTheme.typography.bodyLarge,
          )
        }
      }
    }
  }
}

@Preview
@Composable
fun BasePreview() {
  ProgrammerCalculatorTheme {
    Base(name = "HEX", result = "0", active = true, fun() {})
  }
}
