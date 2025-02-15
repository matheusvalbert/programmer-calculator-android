package com.matheusvalbert.programmercalculator.core.usecase.expression;

import com.matheusvalbert.programmercalculator.core.usecase.UseCase;
import com.matheusvalbert.programmercalculator.core.util.Input;
import com.matheusvalbert.programmercalculator.ui.util.Selection;

public interface ExpressionUseCase extends UseCase {
    String invoke(String expression, @Input int input, Selection selection);
    String invoke(String expression, String input, Selection selection);
}
