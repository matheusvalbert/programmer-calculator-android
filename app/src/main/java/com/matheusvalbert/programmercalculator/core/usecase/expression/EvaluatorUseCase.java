package com.matheusvalbert.programmercalculator.core.usecase.expression;

import com.matheusvalbert.programmercalculator.core.usecase.UseCase;
import com.matheusvalbert.programmercalculator.core.util.Base;

import java.math.BigInteger;

public interface EvaluatorUseCase extends UseCase {
    BigInteger invoke(String expression, @Base int base);
}
