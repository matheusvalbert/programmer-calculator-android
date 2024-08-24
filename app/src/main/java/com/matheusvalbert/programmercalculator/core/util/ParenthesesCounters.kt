package com.matheusvalbert.programmercalculator.core.util

fun String.numberOfOpenParentheses(position: Int): Int {
  var numberOfOpenParentheses = 0
  var i = 0
  while (i < position) {
    if (this[i] == '(') numberOfOpenParentheses++
    i++
  }
  return numberOfOpenParentheses
}

fun String.numberOfCloseParentheses() = count { it == ')' }
