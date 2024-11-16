package com.matheusvalbert.programmercalculator.ui.components

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.isUnspecified
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.matheusvalbert.programmercalculator.core.CalculatorViewModel
import com.matheusvalbert.programmercalculator.core.event.InputEvent

@Composable
fun AutoResizedTextField(
  modifier: Modifier = Modifier,
  calculatorViewModel: CalculatorViewModel = viewModel(),
  textColor: Color = MaterialTheme.colorScheme.primary,
  style: TextStyle = MaterialTheme.typography.bodyLarge.copy(
    fontSize = 100.sp,
    textAlign = TextAlign.End,
    color = textColor
  )
) {

  val focusRequester = remember {
    FocusRequester()
  }

  var resizedTextStyle by remember {
    mutableStateOf(style)
  }

  var shouldDraw by remember {
    mutableStateOf(false)
  }

  var text by remember {
    mutableStateOf(TextFieldValue(""))
  }

  var isFirstRenderer by remember {
    mutableIntStateOf(0)
  }

  calculatorViewModel.onInputChanged {
    text = TextFieldValue(
      calculatorViewModel.result.value.input,
      TextRange(calculatorViewModel.result.value.cursorPosition)
    )
  }

  LaunchedEffect(Unit) {
    focusRequester.requestFocus()
  }

  val textSelectionColors = TextSelectionColors(
    handleColor = MaterialTheme.colorScheme.surfaceVariant,
    backgroundColor = MaterialTheme.colorScheme.surfaceVariant
  )

  CompositionLocalProvider(LocalTextSelectionColors provides textSelectionColors) {
    DisableKeyboard {
      BasicTextField(
        value = text,
        onValueChange = {
          calculatorViewModel.onInputEvent(InputEvent.Keyboard(it.text))
          calculatorViewModel.onInputEvent(InputEvent.ChangeInputPosition(it.selection.start))
        },
        singleLine = true,
        modifier = modifier
          .drawWithContent {
            if (text.text.isBlank() && isFirstRenderer == 0) {
              isFirstRenderer = 1
              resizedTextStyle = style
              shouldDraw = false
            }
            if (shouldDraw) {
              drawContent()
              calculatorViewModel.onInputEvent(InputEvent.ChangeInputPosition(text.selection.start))
            }
          }
          .focusRequester(focusRequester),
        textStyle = resizedTextStyle,
        onTextLayout = { result ->
          if (result.didOverflowHeight || result.didOverflowWidth) {
            if (style.fontSize.isUnspecified) {
              resizedTextStyle = resizedTextStyle.copy(
                fontSize = style.fontSize
              )
            }
            resizedTextStyle = resizedTextStyle.copy(
              fontSize = resizedTextStyle.fontSize * 0.95
            )
          } else {
            shouldDraw = true
          }
        },
        cursorBrush = SolidColor(MaterialTheme.colorScheme.surfaceVariant)
      )
    }
  }
}
