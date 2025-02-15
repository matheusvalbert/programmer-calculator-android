package com.matheusvalbert.programmercalculator.ui.util;

import android.widget.EditText;

public class Selection {

    private final int mStart;
    private final int mEnd;

    private Selection(EditText editText) {
        mStart = editText.getSelectionStart();
        mEnd = editText.getSelectionEnd();
    }

    public static Selection createSelection(EditText editText) {
        return new Selection(editText);
    }

    public boolean isSelected() {
        return mStart != mEnd;
    }

    public int getStartIndex() {
        return mStart;
    }

    public int getEndIndex() {
        return mEnd;
    }
}
