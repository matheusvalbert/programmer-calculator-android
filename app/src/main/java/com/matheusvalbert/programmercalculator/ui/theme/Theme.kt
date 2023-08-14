package com.matheusvalbert.programmercalculator.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
  background = BackgroundDark,
  onPrimary = DigitButtonDark,
  primary = TextDark,
  secondary = FunctionButtonDark,
  onTertiary = DisabledDigitButtonDark,
  tertiary = DisabledTextDark,
  surfaceVariant = Green
)

private val LightColorScheme = lightColorScheme(
  background = BackgroundLight,
  onPrimary = DigitButtonLight,
  primary = TextLight,
  secondary = FunctionButtonLight,
  onTertiary = DisabledDigitButtonLight,
  tertiary = DisabledTextLight,
  surfaceVariant = Green,
)

@Composable
fun ProgrammerCalculatorTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit
) {
  val colorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }

    darkTheme -> DarkColorScheme
    else -> LightColorScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}

@Composable
fun ConfigureSystemUi() {
  val systemUiController = rememberSystemUiController()
  val useDarkIcons = !isSystemInDarkTheme()

  systemUiController.setStatusBarColor(
    color = MaterialTheme.colorScheme.background,
    darkIcons = useDarkIcons
  )

  systemUiController.setNavigationBarColor(
    color = MaterialTheme.colorScheme.background,
    darkIcons = useDarkIcons
  )
}
