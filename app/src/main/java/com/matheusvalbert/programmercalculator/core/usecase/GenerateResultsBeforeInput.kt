package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate
import com.matheusvalbert.programmercalculator.core.util.CrashlyticsUtil
import com.matheusvalbert.programmercalculator.core.util.afterEvaluateConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.expression.ExpressionParser
import org.springframework.expression.spel.standard.SpelExpressionParser

class GenerateResultsBeforeInput {
  companion object {
    private const val ERROR = "Overflow"
  }

  private val parser: ExpressionParser = SpelExpressionParser()

  suspend operator fun invoke(state: ResultSate): ResultSate {
    return withContext(Dispatchers.Default) {
      try {
        val result = parser.parseExpression(state.input.afterEvaluateConverter(state.base))
          .getValue(Int::class.java)!!

        val hex = Integer.toHexString(result).toString().uppercase()
        val dec = result.toString()
        val oct = Integer.toOctalString(result)
        val bin = Integer.toBinaryString(result)

        state.copy(hex = hex, dec = dec, oct = oct, bin = bin)
      } catch (e: Exception) {
        CrashlyticsUtil.dumpResultState(result = state, exception = e)
        state.copy(hex = ERROR, dec = ERROR, oct = ERROR, bin = ERROR)
      }
    }
  }
}
