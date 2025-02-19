package com.matheusvalbert.programmercalculator.core.domain;

import static com.matheusvalbert.programmercalculator.core.util.Base.BIN;
import static com.matheusvalbert.programmercalculator.core.util.Base.DEC;
import static com.matheusvalbert.programmercalculator.core.util.Base.HEX;
import static com.matheusvalbert.programmercalculator.core.util.Base.OCT;
import static com.matheusvalbert.programmercalculator.core.util.Util.OPERATIONS;

import com.matheusvalbert.programmercalculator.core.util.Base;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Tokenizer implements Domain {

    private StringBuilder mExpression;
    private @Base int mBase;

    public List<String> tokenize(String expression, @Base int base) {
        format(expression, base);
        replaceOperators();
        if (mExpression.length() == 0) return Collections.emptyList();
        List<String> tokens = convertToTokens();
        return parseToDecimal(tokens);
    }

    public String format(String expression, @Base int base) {
        mExpression = new StringBuilder(expression);
        mBase = base;
        removeInStart(Set.of(')'));
        removeInStart(OPERATIONS);
        removeInTheEnd(Set.of('('));
        removeInTheEnd(OPERATIONS);
        closeRemainingParentheses();
        return mExpression.toString();
    }

    private void removeInStart(Set<Character> characterToRemove) {
        for (int i = 0; i < mExpression.length(); i++) {
            char c = mExpression.charAt(i);
            if (characterToRemove.contains(c)) mExpression.deleteCharAt(i);
            else break;
        }
    }

    private void removeInTheEnd(Set<Character> characterToRemove) {
        for (int i = mExpression.length(); i > 0; i--) {
            char c = mExpression.charAt(i - 1);
            if (characterToRemove.contains(c)) mExpression.deleteCharAt(i - 1);
            else break;
        }
    }

    private void closeRemainingParentheses() {
        int openParentheses = 0;
        int closeParentheses = 0;

        for (int i = 0; i < mExpression.length(); i++) {
            char c = mExpression.charAt(i);
            if (c == '(') {
                openParentheses++;
            } else if (c == ')') {
                closeParentheses++;
            }
        }

        int remainingCloseParentheses = openParentheses - closeParentheses;

        if (remainingCloseParentheses <= 0) return;

        for (int i = 0; i < remainingCloseParentheses; i++) {
            mExpression.append(')');
        }
    }

    private void replaceOperators() {
        for (int i = 0; i < mExpression.length(); i++) {
            char c = mExpression.charAt(i);
            if (c == '÷') {
                mExpression.setCharAt(i, '/');
            } else if (c == '×') {
                mExpression.setCharAt(i, '*');
            }
        }
    }

    private List<String> convertToTokens() {
        List<String> splittedExpression = new ArrayList<>();
        StringBuilder value = new StringBuilder();

        for (int i = 0; i < mExpression.length(); i++) {
            char c = mExpression.charAt(i);

            if (!Character.isLetterOrDigit(c)) {
                if (value.length() > 0) {
                    splittedExpression.add(value.toString());
                    value.setLength(0);
                }
                splittedExpression.add(String.valueOf(c));
            } else {
                value.append(c);
            }
        }

        if (value.length() > 0) {
            splittedExpression.add(value.toString());
        }

        return splittedExpression;
    }


    private List<String> parseToDecimal(List<String> tokens) {
        List<String> result = new ArrayList<>();
        for (String token : tokens) {
            if (!Character.isLetterOrDigit(token.charAt(0))) {
                result.add(token);
                continue;
            }
            BigInteger convertedValue;
            switch (mBase) {
                case HEX -> convertedValue = new BigInteger(token, 16);
                case DEC -> convertedValue = new BigInteger(token, 10);
                case OCT -> convertedValue = new BigInteger(token, 8);
                case BIN -> convertedValue = new BigInteger(token, 2);
                default -> throw new IllegalStateException("Invalid base conversion during format");
            }
            result.add(String.valueOf(convertedValue));
        }
        return result;
    }
}
