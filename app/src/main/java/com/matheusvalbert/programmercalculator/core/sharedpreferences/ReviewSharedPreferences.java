package com.matheusvalbert.programmercalculator.core.sharedpreferences;

public interface ReviewSharedPreferences extends SharedPreferences {
    void addNewInteraction();
    int getNumberOfInteractions();
    void resetInteractions();
}
