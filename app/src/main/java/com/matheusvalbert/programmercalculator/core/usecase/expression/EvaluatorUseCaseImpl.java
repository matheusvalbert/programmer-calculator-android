package com.matheusvalbert.programmercalculator.core.usecase.expression;

import com.matheusvalbert.programmercalculator.core.domain.Evaluator;
import com.matheusvalbert.programmercalculator.core.domain.Tokenizer;
import com.matheusvalbert.programmercalculator.core.util.Base;

import java.util.List;

public class EvaluatorUseCaseImpl implements EvaluatorUseCase {

    private final Tokenizer mTokenizer;
    private final Evaluator mEvaluator;

    public EvaluatorUseCaseImpl(Tokenizer tokenizer, Evaluator evaluator) {
        mTokenizer = tokenizer;
        mEvaluator = evaluator;
    }

    @Override
    public int invoke(String expression, @Base int base) {
        List<String> tokens = mTokenizer.tokenize(expression, base);
        return mEvaluator.evaluate(tokens);
    }
}
