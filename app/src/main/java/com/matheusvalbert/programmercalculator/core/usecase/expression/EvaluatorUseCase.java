package com.matheusvalbert.programmercalculator.core.usecase.expression;

import com.matheusvalbert.programmercalculator.core.usecase.UseCase;
import com.matheusvalbert.programmercalculator.core.util.Base;

public interface EvaluatorUseCase extends UseCase {
    int invoke(String expression, @Base int base);
}
