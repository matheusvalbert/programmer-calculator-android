package com.matheusvalbert.programmercalculator.core.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class HistorySharedPreferencesImpl implements HistorySharedPreferences {

    private static final String HISTORY_SHARED_PREFERENCES = "history_shared_preferences";
    private static final String HISTORY_AVAILABILITY = "history_availability";

    private final SharedPreferences mSharedPreferences;

    public HistorySharedPreferencesImpl(Context context) {
        mSharedPreferences =
            context.getSharedPreferences(HISTORY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public void setHistoryAvailable(boolean isAvailable) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(HISTORY_AVAILABILITY, isAvailable);
        editor.apply();
    }

    @Override
    public boolean isHistoryAvailable() {
        return mSharedPreferences.getBoolean(HISTORY_AVAILABILITY, false);
    }
}
