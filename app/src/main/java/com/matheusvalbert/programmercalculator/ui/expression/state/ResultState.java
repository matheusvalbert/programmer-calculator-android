package com.matheusvalbert.programmercalculator.ui.expression.state;

import static com.matheusvalbert.programmercalculator.core.util.Base.BIN;
import static com.matheusvalbert.programmercalculator.core.util.Base.DEC;
import static com.matheusvalbert.programmercalculator.core.util.Base.HEX;
import static com.matheusvalbert.programmercalculator.core.util.Base.OCT;
import static com.matheusvalbert.programmercalculator.ui.util.Constants.ERROR;

import com.matheusvalbert.programmercalculator.core.util.Base;

import java.math.BigInteger;

public class ResultState {
    private String mHex = "0";
    private String mDec = "0";
    private String mOct = "0";
    private String mBin = "0";

    public static ResultState createResultState() {
        return new ResultState();
    }

    public void updateResult(BigInteger result) {
        mHex = result.toString(16).toUpperCase();
        mDec = result.toString(10);
        mOct = result.toString(8);
        mBin = result.toString(2);
    }

    public boolean isError() {
        return mHex.equals(ERROR);
    }

    public String getResult(@Base int base) {
        return switch (base) {
            case HEX -> mHex;
            case DEC -> mDec;
            case OCT -> mOct;
            case BIN -> mBin;
            default -> throw new IllegalStateException("Invalid base input");
        };
    }

    public void showError() {
        mHex = mDec = mOct = mBin = ERROR;
    }
}
