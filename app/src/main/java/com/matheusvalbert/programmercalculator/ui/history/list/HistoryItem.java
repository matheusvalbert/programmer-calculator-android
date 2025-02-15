package com.matheusvalbert.programmercalculator.ui.history.list;

import com.matheusvalbert.programmercalculator.core.util.Base;

import java.util.Objects;

public class HistoryItem {

    private final int mId;
    private final @Base int mBase;
    private final String mExpression;
    private final String mResult;

    public HistoryItem(int id, @Base int base, String expression, String result) {
        mId = id;
        mBase = base;
        mExpression = expression;
        mResult = result;
    }

    public int getId() {
        return mId;
    }

    public int getBase() {
        return mBase;
    }

    public String getExpression() {
        return mExpression;
    }

    public String getResult() {
        return mResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryItem that = (HistoryItem) o;
        return mId == that.mId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId);
    }
}
