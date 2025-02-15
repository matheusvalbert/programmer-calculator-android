package com.matheusvalbert.programmercalculator.core.domain;

import static com.matheusvalbert.programmercalculator.core.util.Input.A;
import static com.matheusvalbert.programmercalculator.core.util.Input.B;
import static com.matheusvalbert.programmercalculator.core.util.Input.C;
import static com.matheusvalbert.programmercalculator.core.util.Input.CLEAR;
import static com.matheusvalbert.programmercalculator.core.util.Input.CLOSE_PARENTHESES;
import static com.matheusvalbert.programmercalculator.core.util.Input.D;
import static com.matheusvalbert.programmercalculator.core.util.Input.DELETE;
import static com.matheusvalbert.programmercalculator.core.util.Input.DIVIDE;
import static com.matheusvalbert.programmercalculator.core.util.Input.E;
import static com.matheusvalbert.programmercalculator.core.util.Input.EIGHT;
import static com.matheusvalbert.programmercalculator.core.util.Input.F;
import static com.matheusvalbert.programmercalculator.core.util.Input.FIVE;
import static com.matheusvalbert.programmercalculator.core.util.Input.FOUR;
import static com.matheusvalbert.programmercalculator.core.util.Input.MINUS;
import static com.matheusvalbert.programmercalculator.core.util.Input.NINE;
import static com.matheusvalbert.programmercalculator.core.util.Input.ONE;
import static com.matheusvalbert.programmercalculator.core.util.Input.OPEN_PARENTHESES;
import static com.matheusvalbert.programmercalculator.core.util.Input.PLUS;
import static com.matheusvalbert.programmercalculator.core.util.Input.SEVEN;
import static com.matheusvalbert.programmercalculator.core.util.Input.SHIFT_LEFT;
import static com.matheusvalbert.programmercalculator.core.util.Input.SHIFT_RIGHT;
import static com.matheusvalbert.programmercalculator.core.util.Input.SIX;
import static com.matheusvalbert.programmercalculator.core.util.Input.THREE;
import static com.matheusvalbert.programmercalculator.core.util.Input.TIMES;
import static com.matheusvalbert.programmercalculator.core.util.Input.TWO;
import static com.matheusvalbert.programmercalculator.core.util.Input.ZERO;
import static com.matheusvalbert.programmercalculator.core.util.Util.OPERATIONS;

import android.annotation.SuppressLint;

import com.matheusvalbert.programmercalculator.core.util.Input;
import com.matheusvalbert.programmercalculator.core.util.Util;
import com.matheusvalbert.programmercalculator.ui.util.Selection;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression implements Domain {
    private static final Pattern PATTERN = Pattern.compile("[0-9a-fA-F+\\-*/÷×()]");

    private StringBuilder mExpression;
    private int mIndex;

    @SuppressLint("SwitchIntDef")
    public void newInput(String expression, @Input int input, Selection selection) {
        mExpression = new StringBuilder(expression);
        deleteSelectedIfNeeded(selection);
        mIndex = selection.getStartIndex();
        switch (input) {
            case ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, A, B, C, D, E, F:
                digit(input);
                break;
            case DIVIDE, TIMES, MINUS, PLUS:
                operation(input);
                break;
            case OPEN_PARENTHESES:
                openParentheses();
                break;
            case CLOSE_PARENTHESES:
                closeParentheses();
                break;
            case SHIFT_LEFT:
                shiftLeft();
                break;
            case SHIFT_RIGHT:
                shiftRight();
                break;
            case DELETE:
                delete();
                break;
            case CLEAR:
                clear();
                break;
            default:
                throw new IllegalArgumentException("Valid input is required (except equal)");
        }
    }

    public void newInput(String expression, String input, Selection selection) {
        mExpression = new StringBuilder(expression);
        deleteSelectedIfNeeded(selection);
        Matcher matcher = PATTERN.matcher(input);
        char c;
        int index = selection.getStartIndex();

        while (matcher.find()) {
            c = matcher.group().charAt(0);
            if (c == '/') {
                mExpression.insert(index++,'÷');
            } else if (c == '*') {
                mExpression.insert(index++,'×');
            } else {
                mExpression.insert(index++, Character.toUpperCase(c));
            }
        }
    }

    public String getExpression() {
        return mExpression.toString();
    }

    private void digit(@Input int input) {
        if (!isBlank() && isCloseParentheses()) {
            add(TIMES);
            mIndex++;
        }
        add(input);
    }

    private void operation(@Input int input) {
        if (isBlank() || isOpenParentheses()) {
            return;
        }
        if (isOperation()) {
            replace(input);
            return;
        }
        add(input);
    }

    private void openParentheses() {
        if (!isBlank() && !isOperation() && !isOpenParentheses()) {
            add(TIMES);
            mIndex++;
        }
        add(OPEN_PARENTHESES);
    }

    private void closeParentheses() {
        if (isBlank() || isOperation() || isOpenParentheses() || !canCloseParentheses()) {
            return;
        }
        add(CLOSE_PARENTHESES);
        if (!isLastPosition()) {
            mIndex++;
            add(TIMES);
        }
    }

    private void shiftLeft() {
        if (isBlank()) {
            return;
        }
        mIndex = mExpression.length();
        add(ZERO);
    }

    private void shiftRight() {
        if (isBlank()) {
            return;
        }
        mIndex = mExpression.length();
        delete();
    }

    private void clear() {
        mExpression.setLength(0);
    }

    private boolean isBlank() {
        return mExpression.length() == 0;
    }

    private boolean cannotCheckLatestIndex() {
        return mIndex - 1 == -1;
    }

    private boolean isCloseParentheses() {
        if (cannotCheckLatestIndex()) return false;
        return mExpression.charAt(mIndex - 1) == ')';
    }

    private boolean isOpenParentheses() {
        if (cannotCheckLatestIndex()) return false;
        return mExpression.charAt(mIndex - 1) == '(';
    }

    private boolean isOperation() {
        if (cannotCheckLatestIndex()) return true;
        char lastCharacter = mExpression.charAt(mIndex - 1);
        return OPERATIONS.contains(lastCharacter);
    }

    private boolean canCloseParentheses() {
        char[] chars = new char[mIndex];
        int openParentheses = 0;
        int closeParentheses = 0;

        mExpression.getChars(0, mIndex, chars, 0);

        for (char c : chars) {
            if (c == '(') {
                openParentheses++;
            } else if (c == ')') {
                closeParentheses++;
            }
        }

        return openParentheses > closeParentheses;
    }

    private boolean isLastPosition() {
        return mIndex + 1 == mExpression.length();
    }

    private void add(@Input int input) {
        mExpression.insert(mIndex, Util.getChar(input));
    }

    private void delete() {
        if (isBlank() || cannotCheckLatestIndex()) {
            return;
        }
        mExpression.deleteCharAt(mIndex - 1);
    }

    private void deleteSelectedIfNeeded(Selection selection) {
        if (!selection.isSelected()) return;
        mExpression.delete(selection.getStartIndex(), selection.getEndIndex());
    }

    private void replace(@Input int input) {
        mExpression.setCharAt(mIndex - 1, Util.getChar(input));
    }
}
