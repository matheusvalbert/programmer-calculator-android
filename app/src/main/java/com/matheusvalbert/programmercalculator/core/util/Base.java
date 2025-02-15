package com.matheusvalbert.programmercalculator.core.util;

import static com.matheusvalbert.programmercalculator.core.util.Base.BIN;
import static com.matheusvalbert.programmercalculator.core.util.Base.DEC;
import static com.matheusvalbert.programmercalculator.core.util.Base.HEX;
import static com.matheusvalbert.programmercalculator.core.util.Base.OCT;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;

@Retention(SOURCE)
@IntDef({HEX, DEC, OCT, BIN})
public @interface Base {
    int HEX = 0;
    int DEC = 1;
    int OCT = 2;
    int BIN = 3;
}
