package com.matheusvalbert.programmercalculator.core.util

fun String.numberOfOpenParentheses() = count { it == '(' }

fun String.numberOfCloseParentheses() = count { it == ')' }
