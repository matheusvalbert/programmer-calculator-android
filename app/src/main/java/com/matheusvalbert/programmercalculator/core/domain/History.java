package com.matheusvalbert.programmercalculator.core.domain;

import com.matheusvalbert.programmercalculator.core.util.Base;

public class History implements Domain {
    private final Integer mId;
    private String mExpression;
    private final @Base int mBase;
    private final String mResult;

    public History(Integer id, String expression, @Base int base, String result) {
        mId = id;
        mExpression = expression;
        mBase = base;
        mResult = result;
    }

    public History(String expression, @Base int base, String result) {
        this(null, expression, base, result);
    }

    public Integer getId() {
        return mId;
    }

    public String getExpression() {
        return mExpression;
    }

    public int getBase() {
        return mBase;
    }

    public String getResult() {
        return mResult;
    }

    public void updateExpression(String expression) {
        mExpression = expression;
    }
}
