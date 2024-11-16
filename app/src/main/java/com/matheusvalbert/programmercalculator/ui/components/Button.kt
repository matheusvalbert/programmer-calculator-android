package com.matheusvalbert.programmercalculator.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Button(
  symbol: String,
  height: Int,
  width: Int,
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
  backgroundColor: Color = MaterialTheme.colorScheme.onPrimary,
  disabledBackgroundColor: Color = MaterialTheme.colorScheme.onTertiary,
  enabled: Boolean = true,
  textColor: Color = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary
) {
  Button(
    onClick = onClick,
    colors = ButtonDefaults.buttonColors(
      containerColor = backgroundColor,
      disabledContainerColor = disabledBackgroundColor,
    ),
    modifier = modifier
      .padding(4.dp)
      .height(height.dp)
      .width(width.dp),
    shape = RoundedCornerShape(16.dp),
    enabled = enabled,
  ) {
    AutoResizedText(text = symbol, textColor = textColor)
  }
}
