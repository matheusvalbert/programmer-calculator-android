package com.matheusvalbert.programmercalculator.ui.expression.state;

import static com.matheusvalbert.programmercalculator.ui.expression.state.ResultState.createResultState;

import com.matheusvalbert.programmercalculator.core.util.Base;
import com.matheusvalbert.programmercalculator.ui.base.State;

import java.math.BigInteger;

public class ExpressionState implements State {
    private @Base int mBase;
    private int mCursorPosition;
    private String mExpression;
    private final ResultState mResultState;

    private ExpressionState(@Base int base, int cursorPosition, String expression) {
        mBase = base;
        mCursorPosition = cursorPosition;
        mExpression = expression;
        mResultState = createResultState();
    }

    public static ExpressionState createCalculatorState() {
        return new ExpressionState(Base.HEX, 0, "");
    }

    public void updateBase(@Base int base) {
        mBase = base;
    }

    public void updateInput(int cursorPosition, String expression) {
        mCursorPosition = calculateNewCursorPosition(cursorPosition, expression);
        mExpression = expression;
        if (expression.isEmpty()) updateResult(BigInteger.valueOf(0L));
    }

    public void updateResult(BigInteger result) {
        mResultState.updateResult(result);
    }

    public void resultToInput() {
        if (mExpression.isEmpty() || mResultState.isError()) return;
        mExpression = mResultState.getResult(mBase);
    }

    public int getBase() {
        return mBase;
    }

    public int getCursorPosition() {
        return Math.min(mCursorPosition, mExpression.length());
    }

    public String getExpression() {
        return mExpression;
    }

    public String getResult(@Base int base) {
        return mResultState.getResult(base);
    }

    private int calculateNewCursorPosition(int cursorPosition, String expression) {
        int newCursorPosition = cursorPosition + (expression.length() - mExpression.length());
        return Math.max(newCursorPosition, 0);
    }

    public void showError() {
        mResultState.showError();
    }
}
