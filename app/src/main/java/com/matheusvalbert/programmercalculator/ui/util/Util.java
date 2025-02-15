package com.matheusvalbert.programmercalculator.ui.util;

import static com.matheusvalbert.programmercalculator.ui.util.Selection.createSelection;

import android.view.KeyEvent;
import android.widget.EditText;

import androidx.annotation.StringRes;

import com.matheusvalbert.programmercalculator.R;
import com.matheusvalbert.programmercalculator.core.util.Base;
import com.matheusvalbert.programmercalculator.core.util.Input;
import com.matheusvalbert.programmercalculator.databinding.ViewKeyboardBinding;
import com.matheusvalbert.programmercalculator.ui.expression.ExpressionViewModel;

import java.util.Set;

public class Util {
    public static final Set<Integer> HEX_INPUT = Set.of(KeyEvent.KEYCODE_0, KeyEvent.KEYCODE_1,
            KeyEvent.KEYCODE_2, KeyEvent.KEYCODE_3, KeyEvent.KEYCODE_4, KeyEvent.KEYCODE_5,
            KeyEvent.KEYCODE_6, KeyEvent.KEYCODE_7, KeyEvent.KEYCODE_8, KeyEvent.KEYCODE_9,
            KeyEvent.KEYCODE_A, KeyEvent.KEYCODE_B, KeyEvent.KEYCODE_C, KeyEvent.KEYCODE_D,
            KeyEvent.KEYCODE_E, KeyEvent.KEYCODE_F, KeyEvent.KEYCODE_DEL, KeyEvent.KEYCODE_SLASH,
            KeyEvent.KEYCODE_STAR, KeyEvent.KEYCODE_MINUS, KeyEvent.KEYCODE_PLUS, KeyEvent.KEYCODE_EQUALS,
            KeyEvent.KEYCODE_NUMPAD_LEFT_PAREN, KeyEvent.KEYCODE_NUMPAD_RIGHT_PAREN);

    public static final Set<Integer> DEC_INPUT = Set.of(KeyEvent.KEYCODE_0, KeyEvent.KEYCODE_1,
            KeyEvent.KEYCODE_2, KeyEvent.KEYCODE_3, KeyEvent.KEYCODE_4, KeyEvent.KEYCODE_5,
            KeyEvent.KEYCODE_6, KeyEvent.KEYCODE_7, KeyEvent.KEYCODE_8, KeyEvent.KEYCODE_9,
            KeyEvent.KEYCODE_DEL, KeyEvent.KEYCODE_SLASH, KeyEvent.KEYCODE_STAR,
            KeyEvent.KEYCODE_MINUS, KeyEvent.KEYCODE_PLUS, KeyEvent.KEYCODE_EQUALS,
            KeyEvent.KEYCODE_NUMPAD_LEFT_PAREN, KeyEvent.KEYCODE_NUMPAD_RIGHT_PAREN);

    public static final Set<Integer> OCT_INPUT = Set.of(KeyEvent.KEYCODE_0, KeyEvent.KEYCODE_1,
            KeyEvent.KEYCODE_2, KeyEvent.KEYCODE_3, KeyEvent.KEYCODE_4, KeyEvent.KEYCODE_5,
            KeyEvent.KEYCODE_6, KeyEvent.KEYCODE_7, KeyEvent.KEYCODE_DEL, KeyEvent.KEYCODE_SLASH,
            KeyEvent.KEYCODE_STAR, KeyEvent.KEYCODE_MINUS, KeyEvent.KEYCODE_PLUS, KeyEvent.KEYCODE_EQUALS,
            KeyEvent.KEYCODE_NUMPAD_LEFT_PAREN, KeyEvent.KEYCODE_NUMPAD_RIGHT_PAREN);

    public static final Set<Integer> BIN_INPUT = Set.of(KeyEvent.KEYCODE_0, KeyEvent.KEYCODE_1,
            KeyEvent.KEYCODE_DEL, KeyEvent.KEYCODE_SLASH, KeyEvent.KEYCODE_STAR,
            KeyEvent.KEYCODE_MINUS, KeyEvent.KEYCODE_PLUS, KeyEvent.KEYCODE_EQUALS,
            KeyEvent.KEYCODE_NUMPAD_LEFT_PAREN, KeyEvent.KEYCODE_NUMPAD_RIGHT_PAREN);

    public static void mapKeyboard(ViewKeyboardBinding binding, EditText expression, ExpressionViewModel viewModel) {
        binding.clear.setOnClickListener(v -> viewModel.newInput(Input.CLEAR, createSelection(expression)));
        binding.openParentheses.setOnClickListener(v -> viewModel.newInput(Input.OPEN_PARENTHESES, createSelection(expression)));
        binding.closeParentheses.setOnClickListener(v -> viewModel.newInput(Input.CLOSE_PARENTHESES, createSelection(expression)));
        binding.shiftLeft.setOnClickListener(v -> viewModel.newInput(Input.SHIFT_LEFT, createSelection(expression)));
        binding.d.setOnClickListener(v -> viewModel.newInput(Input.D, createSelection(expression)));
        binding.e.setOnClickListener(v -> viewModel.newInput(Input.E, createSelection(expression)));
        binding.f.setOnClickListener(v -> viewModel.newInput(Input.F, createSelection(expression)));
        binding.shiftRight.setOnClickListener(v -> viewModel.newInput(Input.SHIFT_RIGHT, createSelection(expression)));
        binding.a.setOnClickListener(v -> viewModel.newInput(Input.A, createSelection(expression)));
        binding.b.setOnClickListener(v -> viewModel.newInput(Input.B, createSelection(expression)));
        binding.c.setOnClickListener(v -> viewModel.newInput(Input.C, createSelection(expression)));
        binding.divide.setOnClickListener(v -> viewModel.newInput(Input.DIVIDE, createSelection(expression)));
        binding.seven.setOnClickListener(v -> viewModel.newInput(Input.SEVEN, createSelection(expression)));
        binding.eight.setOnClickListener(v -> viewModel.newInput(Input.EIGHT, createSelection(expression)));
        binding.nine.setOnClickListener(v -> viewModel.newInput(Input.NINE, createSelection(expression)));
        binding.times.setOnClickListener(v -> viewModel.newInput(Input.TIMES, createSelection(expression)));
        binding.four.setOnClickListener(v -> viewModel.newInput(Input.FOUR, createSelection(expression)));
        binding.five.setOnClickListener(v -> viewModel.newInput(Input.FIVE, createSelection(expression)));
        binding.six.setOnClickListener(v -> viewModel.newInput(Input.SIX, createSelection(expression)));
        binding.minus.setOnClickListener(v -> viewModel.newInput(Input.MINUS, createSelection(expression)));
        binding.one.setOnClickListener(v -> viewModel.newInput(Input.ONE, createSelection(expression)));
        binding.two.setOnClickListener(v -> viewModel.newInput(Input.TWO, createSelection(expression)));
        binding.three.setOnClickListener(v -> viewModel.newInput(Input.THREE, createSelection(expression)));
        binding.plus.setOnClickListener(v -> viewModel.newInput(Input.PLUS, createSelection(expression)));
        binding.zero.setOnClickListener(v -> viewModel.newInput(Input.ZERO, createSelection(expression)));
        binding.delete.setOnClickListener(v -> viewModel.newInput(Input.DELETE, createSelection(expression)));
        binding.equal.setOnClickListener(v -> viewModel.onEqual());
    }

    public static @Input int getInput(int keyCode) {
        return switch (keyCode) {
            case KeyEvent.KEYCODE_0 -> Input.ZERO;
            case KeyEvent.KEYCODE_1 -> Input.ONE;
            case KeyEvent.KEYCODE_2 -> Input.TWO;
            case KeyEvent.KEYCODE_3 -> Input.THREE;
            case KeyEvent.KEYCODE_4 -> Input.FOUR;
            case KeyEvent.KEYCODE_5 -> Input.FIVE;
            case KeyEvent.KEYCODE_6 -> Input.SIX;
            case KeyEvent.KEYCODE_7 -> Input.SEVEN;
            case KeyEvent.KEYCODE_8 -> Input.EIGHT;
            case KeyEvent.KEYCODE_9 -> Input.NINE;
            case KeyEvent.KEYCODE_A -> Input.A;
            case KeyEvent.KEYCODE_B -> Input.B;
            case KeyEvent.KEYCODE_C -> Input.C;
            case KeyEvent.KEYCODE_D -> Input.D;
            case KeyEvent.KEYCODE_E -> Input.E;
            case KeyEvent.KEYCODE_F -> Input.F;
            case KeyEvent.KEYCODE_DEL -> Input.DELETE;
            case KeyEvent.KEYCODE_SLASH -> Input.DIVIDE;
            case KeyEvent.KEYCODE_STAR -> Input.TIMES;
            case KeyEvent.KEYCODE_MINUS -> Input.MINUS;
            case KeyEvent.KEYCODE_PLUS -> Input.PLUS;
            case KeyEvent.KEYCODE_EQUALS -> Input.EQUAL;
            case KeyEvent.KEYCODE_NUMPAD_LEFT_PAREN -> Input.OPEN_PARENTHESES;
            case KeyEvent.KEYCODE_NUMPAD_RIGHT_PAREN -> Input.CLOSE_PARENTHESES;
            default -> throw new IllegalStateException("Valid input is required");
        };
    }

    public static @StringRes int getBaseString(@Base int base) {
        return switch (base) {
            case Base.HEX -> R.string.hexadecimal;
            case Base.DEC -> R.string.decimal;
            case Base.OCT -> R.string.octal;
            case Base.BIN -> R.string.binary;
            default -> throw new IllegalArgumentException("Valid base is required");
        };
    }
}
