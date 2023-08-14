package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate
import com.matheusvalbert.programmercalculator.core.util.afterEvaluateConverter
import org.springframework.expression.ExpressionParser
import org.springframework.expression.spel.standard.SpelExpressionParser

class GenerateResultsBeforeInput {

  private val parser: ExpressionParser = SpelExpressionParser()

  operator fun invoke(state: ResultSate): ResultSate {
    return try {
      val result = parser.parseExpression(state.input.afterEvaluateConverter(state.baseInput))
        .getValue(Int::class.java)!!

      val hex = Integer.toHexString(result).toString().uppercase()
      val dec = result.toString()
      val oct = Integer.toOctalString(result)
      val bin = Integer.toBinaryString(result)

      state.copy(hex = hex, dec = dec, oct = oct, bin = bin)
    } catch (e: Exception) {
      val error = "Overflow"
      state.copy(hex = error, dec = error, oct = error, bin = error)
    }
  }
}