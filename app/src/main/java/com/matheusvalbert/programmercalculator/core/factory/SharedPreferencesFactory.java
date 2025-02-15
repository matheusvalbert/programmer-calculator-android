package com.matheusvalbert.programmercalculator.core.factory;

import android.content.Context;

import com.matheusvalbert.programmercalculator.core.sharedpreferences.HistorySharedPreferences;
import com.matheusvalbert.programmercalculator.core.sharedpreferences.HistorySharedPreferencesImpl;
import com.matheusvalbert.programmercalculator.core.sharedpreferences.ReviewSharedPreferences;
import com.matheusvalbert.programmercalculator.core.sharedpreferences.ReviewSharedPreferencesImpl;
import com.matheusvalbert.programmercalculator.core.sharedpreferences.SharedPreferences;

import java.io.InvalidClassException;

public class SharedPreferencesFactory {
    public static <T extends SharedPreferences> SharedPreferences createSharedPreferences(Context context, Class<T> clazz) throws InvalidClassException {
        if (clazz.equals(ReviewSharedPreferences.class)) {
            return new ReviewSharedPreferencesImpl(context);
        } else if (clazz.equals(HistorySharedPreferences.class)) {
            return new HistorySharedPreferencesImpl(context);
        } else {
            throw new InvalidClassException("must be an existent shared preferences class");
        }
    }
}
