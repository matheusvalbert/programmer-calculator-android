package com.matheusvalbert.programmercalculator.core.util

fun String.afterCursorValue(position: Int): Char {
  return if(position == 0) '+' else this[position-1]
}

fun String.substituteCharacter(character: String, position: Int): String {
  return substring(0, position - 1) + character + substring(position)
}

fun String.addCharacterInPosition(character: String, position: Int): String {
  return substring(0, position) + character + substring(position)
}

fun String.dropValueAfterCursor(position: Int): String {
  return substring(0, position - 1) + substring(position)
}
