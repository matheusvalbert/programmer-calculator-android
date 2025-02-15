package com.matheusvalbert.programmercalculator.core.usecase.expression;

import com.matheusvalbert.programmercalculator.core.listener.RequestReviewListener;

import com.matheusvalbert.programmercalculator.core.sharedpreferences.ReviewSharedPreferences;

public class RequestReviewUseCaseImpl implements RequestReviewUseCase {
    private static final int NUMBER_OF_INTERACTIONS_BEFORE_REQUEST = 15;

    private final ReviewSharedPreferences mReviewSharedPreferences;

    public RequestReviewUseCaseImpl(ReviewSharedPreferences reviewSharedPreferences) {
        mReviewSharedPreferences = reviewSharedPreferences;
    }

    @Override
    public void invoke(RequestReviewListener requestReviewListener) {
        int numberOfInteractions = mReviewSharedPreferences.getNumberOfInteractions();
        if (numberOfInteractions < NUMBER_OF_INTERACTIONS_BEFORE_REQUEST) {
            mReviewSharedPreferences.addNewInteraction();
            return;
        }
        mReviewSharedPreferences.resetInteractions();
        requestReviewListener.onRequestReviewRequired();
    }
}
