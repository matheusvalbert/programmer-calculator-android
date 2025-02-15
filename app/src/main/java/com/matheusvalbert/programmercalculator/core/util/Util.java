package com.matheusvalbert.programmercalculator.core.util;

import static com.matheusvalbert.programmercalculator.core.util.Input.A;
import static com.matheusvalbert.programmercalculator.core.util.Input.B;
import static com.matheusvalbert.programmercalculator.core.util.Input.C;
import static com.matheusvalbert.programmercalculator.core.util.Input.CLOSE_PARENTHESES;
import static com.matheusvalbert.programmercalculator.core.util.Input.D;
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
import static com.matheusvalbert.programmercalculator.core.util.Input.SIX;
import static com.matheusvalbert.programmercalculator.core.util.Input.THREE;
import static com.matheusvalbert.programmercalculator.core.util.Input.TIMES;
import static com.matheusvalbert.programmercalculator.core.util.Input.TWO;
import static com.matheusvalbert.programmercalculator.core.util.Input.ZERO;

import android.annotation.SuppressLint;

import java.util.Set;

public class Util {
    public static final Set<Character> OPERATIONS = Set.of('÷', '×', '-', '+');

    @SuppressLint("SwitchIntDef")
    public static char getChar(@Input int input) {
        return switch (input) {
            case ZERO -> '0';
            case ONE -> '1';
            case TWO -> '2';
            case THREE -> '3';
            case FOUR -> '4';
            case FIVE -> '5';
            case SIX -> '6';
            case SEVEN -> '7';
            case EIGHT -> '8';
            case NINE -> '9';
            case A -> 'A';
            case B -> 'B';
            case C -> 'C';
            case D -> 'D';
            case E -> 'E';
            case F -> 'F';
            case DIVIDE -> '÷';
            case TIMES -> '×';
            case MINUS -> '-';
            case PLUS -> '+';
            case OPEN_PARENTHESES -> '(';
            case CLOSE_PARENTHESES -> ')';
            default ->
                    throw new IllegalArgumentException("Digit or operation or parentheses required");
        };
    }
}
