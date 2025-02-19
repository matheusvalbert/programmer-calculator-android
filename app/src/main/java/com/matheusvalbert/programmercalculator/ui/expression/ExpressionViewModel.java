package com.matheusvalbert.programmercalculator.ui.expression;

import com.matheusvalbert.programmercalculator.core.domain.History;
import com.matheusvalbert.programmercalculator.core.listener.RequestReviewListener;
import com.matheusvalbert.programmercalculator.core.listener.ResultListener;
import com.matheusvalbert.programmercalculator.core.usecase.expression.EvaluatorUseCase;
import com.matheusvalbert.programmercalculator.core.usecase.expression.ExpressionUseCase;
import com.matheusvalbert.programmercalculator.core.usecase.expression.RequestReviewUseCase;
import com.matheusvalbert.programmercalculator.core.util.Base;
import com.matheusvalbert.programmercalculator.core.util.Input;
import com.matheusvalbert.programmercalculator.ui.base.ErrorHandler;
import com.matheusvalbert.programmercalculator.ui.expression.state.ExpressionState;
import com.matheusvalbert.programmercalculator.ui.base.BaseViewModel;
import com.matheusvalbert.programmercalculator.ui.history.mapper.HistoryMapper;
import com.matheusvalbert.programmercalculator.ui.util.Selection;

import java.math.BigInteger;

public class ExpressionViewModel extends BaseViewModel {
    private final ExpressionUseCase mExpressionUseCase;
    private final EvaluatorUseCase mEvaluatorUseCase;
    private final RequestReviewUseCase mRequestReviewUseCase;
    private RequestReviewListener mRequestReviewListener;
    private ResultListener mResultListener;

    private final ErrorHandler errorHandler = _ -> {
        ExpressionState expressionState = (ExpressionState) mUiState.getValue();
        expressionState.showError();
        mUiState.postValue(expressionState);
    };

    public ExpressionViewModel(ExpressionUseCase expressionUseCase, EvaluatorUseCase evaluatorUseCase,
                               RequestReviewUseCase requestReviewUseCase) {
        super(ExpressionState.createCalculatorState());
        mExpressionUseCase = expressionUseCase;
        mEvaluatorUseCase = evaluatorUseCase;
        mRequestReviewUseCase = requestReviewUseCase;
    }

    public void updateRequestReviewListener(RequestReviewListener requestReviewListener) {
        mRequestReviewListener = requestReviewListener;
    }

    public void updateHistoryListener(ResultListener resultListener) {
        mResultListener = resultListener;
    }

    public void updateBase(@Base int base) {
        async(() -> {
            ExpressionState expressionState = (ExpressionState) mUiState.getValue();
            if (expressionState == null) return;
            expressionState.updateBase(base);
            resultToInput(expressionState);
            mUiState.postValue(expressionState);
        }, errorHandler);
    }

    public void newInput(@Input int input, Selection selection) {
        async(() -> {
            ExpressionState expressionState = (ExpressionState) mUiState.getValue();
            String newExpression = mExpressionUseCase.invoke(expressionState.getExpression(), input, selection);
            expressionState.updateInput(selection.getStartIndex(), newExpression);
            evaluate(expressionState);
            mUiState.postValue(expressionState);
            if(input == Input.CLEAR) mRequestReviewUseCase.invoke(mRequestReviewListener);
        }, errorHandler);
    }

    public void newInput(String input, Selection selection) {
        async(() -> {
            ExpressionState expressionState = (ExpressionState) mUiState.getValue();
            String newExpression = mExpressionUseCase.invoke(expressionState.getExpression(), input, selection);
            expressionState.updateInput(selection.getStartIndex(), newExpression);
            evaluate(expressionState);
            mUiState.postValue(expressionState);
        }, errorHandler);
    }

    public void newInputFromHistory(History history) {
        async(() -> {
            ExpressionState expressionState = (ExpressionState) mUiState.getValue();
            expressionState.updateBase(history.getBase());
            expressionState.updateInput(0, history.getExpression());
            evaluate(expressionState);
            mUiState.postValue(expressionState);
        });
    }

    public void onEqual() {
        ExpressionState expressionState = (ExpressionState) mUiState.getValue();
        onNewResult(expressionState);
        resultToInput(expressionState);
    }

    private void resultToInput(ExpressionState expressionState) {
        async(() -> {
            expressionState.resultToInput();
            mUiState.postValue(expressionState);
            mRequestReviewUseCase.invoke(mRequestReviewListener);
        });
    }

    private void onNewResult(ExpressionState expressionState) {
        History history = HistoryMapper.toHistory(expressionState);
        mResultListener.onNewResult(history);
    }

    private void evaluate(ExpressionState expressionState) {
        if (expressionState.getExpression().isEmpty()) return;
        BigInteger result = mEvaluatorUseCase.invoke(expressionState.getExpression(), expressionState.getBase());
        expressionState.updateResult(result);
    }
}
