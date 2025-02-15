package com.matheusvalbert.programmercalculator.core.util;

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
import static com.matheusvalbert.programmercalculator.core.util.Input.EQUAL;
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
import static java.lang.annotation.RetentionPolicy.SOURCE;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;

@Retention(SOURCE)
@IntDef({ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, A, B, C, D, E, F,
        OPEN_PARENTHESES, CLOSE_PARENTHESES, SHIFT_LEFT, SHIFT_RIGHT, DIVIDE, TIMES, MINUS, PLUS,
        EQUAL, DELETE, CLEAR})
public @interface Input {
    int ZERO = 0;
    int ONE = 1;
    int TWO = 2;
    int THREE = 3;
    int FOUR = 4;
    int FIVE = 5;
    int SIX = 6;
    int SEVEN = 7;
    int EIGHT = 8;
    int NINE = 9;
    int A = 10;
    int B = 11;
    int C = 12;
    int D = 13;
    int E = 14;
    int F = 15;
    int OPEN_PARENTHESES = 16;
    int CLOSE_PARENTHESES = 17;
    int SHIFT_LEFT = 18;
    int SHIFT_RIGHT = 19;
    int DIVIDE = 20;
    int TIMES = 21;
    int MINUS = 22;
    int PLUS = 23;
    int EQUAL = 24;
    int DELETE = 25;
    int CLEAR = 26;
}
