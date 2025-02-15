package com.matheusvalbert.programmercalculator.core.usecase.expression;

import com.matheusvalbert.programmercalculator.core.domain.Expression;
import com.matheusvalbert.programmercalculator.core.util.Input;
import com.matheusvalbert.programmercalculator.ui.util.Selection;

public class ExpressionUseCaseImpl implements ExpressionUseCase {

    private final Expression mExpression;

    public ExpressionUseCaseImpl(Expression expression) {
        mExpression = expression;
    }

    @Override
    public String invoke(String expression, @Input int input, Selection selection) {
        mExpression.newInput(expression, input, selection);
        return mExpression.getExpression();
    }

    @Override
    public String invoke(String expression, String input, Selection selection) {
        mExpression.newInput(expression, input, selection);
        return mExpression.getExpression();
    }
}
