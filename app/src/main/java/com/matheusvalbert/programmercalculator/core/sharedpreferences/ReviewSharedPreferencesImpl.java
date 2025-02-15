package com.matheusvalbert.programmercalculator.core.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class ReviewSharedPreferencesImpl implements ReviewSharedPreferences {

    private static final String REVIEW_SHARED_PREFERENCES = "review_shared_preferences";
    private static final String NUMBER_OF_INTERACTIONS = "number_of_interactions";
    private static final int DEFAULT_NUMBER_OF_INTERACTIONS = 0;

    private final SharedPreferences mSharedPreferences;

    public ReviewSharedPreferencesImpl(Context context) {
        mSharedPreferences =
            context.getSharedPreferences(REVIEW_SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public void addNewInteraction() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(NUMBER_OF_INTERACTIONS, getNumberOfInteractions() + 1);
        editor.apply();
    }

    @Override
    public int getNumberOfInteractions() {
        return mSharedPreferences.getInt(NUMBER_OF_INTERACTIONS, DEFAULT_NUMBER_OF_INTERACTIONS);
    }

    @Override
    public void resetInteractions() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(NUMBER_OF_INTERACTIONS, DEFAULT_NUMBER_OF_INTERACTIONS);
        editor.apply();
    }
}
