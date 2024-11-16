package com.matheusvalbert.programmercalculator.core.event

sealed class InputEvent {
  data class Keyboard(val input: String): InputEvent()
  data class ChangeInputPosition(val inputPosition: Int): InputEvent()
  data class Digit(val digit: String) : InputEvent()
  data class Operation(val operation: String): InputEvent()
  data object Clear: InputEvent()
  data object OpenParentheses: InputEvent()
  data object CloseParentheses: InputEvent()
  data object Shl: InputEvent()
  data object Shr: InputEvent()
  data object Delete: InputEvent()
  data object Equal: InputEvent()
}
