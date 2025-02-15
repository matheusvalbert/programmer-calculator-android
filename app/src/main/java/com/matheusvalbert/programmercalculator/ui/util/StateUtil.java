package com.matheusvalbert.programmercalculator.ui.util;

import com.matheusvalbert.programmercalculator.core.util.Base;
import com.matheusvalbert.programmercalculator.databinding.ActivityMainBinding;

public class StateUtil {
    public static void updateButtonState(ActivityMainBinding binding, @Base int base) {
        switch (base) {
            case Base.HEX -> {
                binding.viewKeyboard.two.setEnabled(true);
                binding.viewKeyboard.three.setEnabled(true);
                binding.viewKeyboard.four.setEnabled(true);
                binding.viewKeyboard.five.setEnabled(true);
                binding.viewKeyboard.six.setEnabled(true);
                binding.viewKeyboard.seven.setEnabled(true);
                binding.viewKeyboard.eight.setEnabled(true);
                binding.viewKeyboard.nine.setEnabled(true);
                binding.viewKeyboard.a.setEnabled(true);
                binding.viewKeyboard.b.setEnabled(true);
                binding.viewKeyboard.c.setEnabled(true);
                binding.viewKeyboard.d.setEnabled(true);
                binding.viewKeyboard.e.setEnabled(true);
                binding.viewKeyboard.f.setEnabled(true);
                binding.viewKeyboard.shiftLeft.setEnabled(false);
                binding.viewKeyboard.shiftRight.setEnabled(false);
            }
            case Base.DEC -> {
                binding.viewKeyboard.two.setEnabled(true);
                binding.viewKeyboard.three.setEnabled(true);
                binding.viewKeyboard.four.setEnabled(true);
                binding.viewKeyboard.five.setEnabled(true);
                binding.viewKeyboard.six.setEnabled(true);
                binding.viewKeyboard.seven.setEnabled(true);
                binding.viewKeyboard.eight.setEnabled(true);
                binding.viewKeyboard.nine.setEnabled(true);
                binding.viewKeyboard.a.setEnabled(false);
                binding.viewKeyboard.b.setEnabled(false);
                binding.viewKeyboard.c.setEnabled(false);
                binding.viewKeyboard.d.setEnabled(false);
                binding.viewKeyboard.e.setEnabled(false);
                binding.viewKeyboard.f.setEnabled(false);
                binding.viewKeyboard.shiftLeft.setEnabled(false);
                binding.viewKeyboard.shiftRight.setEnabled(false);
            }
            case Base.OCT -> {
                binding.viewKeyboard.two.setEnabled(true);
                binding.viewKeyboard.three.setEnabled(true);
                binding.viewKeyboard.four.setEnabled(true);
                binding.viewKeyboard.five.setEnabled(true);
                binding.viewKeyboard.six.setEnabled(true);
                binding.viewKeyboard.seven.setEnabled(true);
                binding.viewKeyboard.eight.setEnabled(false);
                binding.viewKeyboard.nine.setEnabled(false);
                binding.viewKeyboard.a.setEnabled(false);
                binding.viewKeyboard.b.setEnabled(false);
                binding.viewKeyboard.c.setEnabled(false);
                binding.viewKeyboard.d.setEnabled(false);
                binding.viewKeyboard.e.setEnabled(false);
                binding.viewKeyboard.f.setEnabled(false);
                binding.viewKeyboard.shiftLeft.setEnabled(false);
                binding.viewKeyboard.shiftRight.setEnabled(false);
            }
            case Base.BIN -> {
                binding.viewKeyboard.two.setEnabled(false);
                binding.viewKeyboard.three.setEnabled(false);
                binding.viewKeyboard.four.setEnabled(false);
                binding.viewKeyboard.five.setEnabled(false);
                binding.viewKeyboard.six.setEnabled(false);
                binding.viewKeyboard.seven.setEnabled(false);
                binding.viewKeyboard.eight.setEnabled(false);
                binding.viewKeyboard.nine.setEnabled(false);
                binding.viewKeyboard.a.setEnabled(false);
                binding.viewKeyboard.b.setEnabled(false);
                binding.viewKeyboard.c.setEnabled(false);
                binding.viewKeyboard.d.setEnabled(false);
                binding.viewKeyboard.e.setEnabled(false);
                binding.viewKeyboard.f.setEnabled(false);
                binding.viewKeyboard.shiftLeft.setEnabled(true);
                binding.viewKeyboard.shiftRight.setEnabled(true);
            }
            default -> throw new IllegalArgumentException("Valid base is required");
        }
    }
}
