package com.matheusvalbert.programmercalculator.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.isUnspecified
import androidx.compose.ui.unit.sp

@Composable
fun AutoResizedText(
  text: String,
  modifier: Modifier = Modifier,
  textColor: Color = MaterialTheme.colorScheme.primary,
  style: TextStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 100.sp),
) {

  var resizedTextStyle by remember {
    mutableStateOf(style)
  }

  var shouldDraw by remember {
    mutableStateOf(false)
  }

  var lastText by remember {
    mutableStateOf(text)
  }

  Text(
    text = text,
    color = textColor,
    maxLines = 1,
    modifier = modifier.drawWithContent {
      if (lastText != text && text == "0") {
        resizedTextStyle = style
        shouldDraw = false
      }
      if (shouldDraw) {
        drawContent()
      }
      lastText = text
    },
    softWrap = false,
    style = resizedTextStyle,
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
    }
  )
}
