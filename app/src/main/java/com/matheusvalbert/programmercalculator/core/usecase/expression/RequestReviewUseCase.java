package com.matheusvalbert.programmercalculator.core.usecase.expression;

import com.matheusvalbert.programmercalculator.core.listener.RequestReviewListener;
import com.matheusvalbert.programmercalculator.core.usecase.UseCase;

public interface RequestReviewUseCase extends UseCase {
    void invoke(RequestReviewListener requestReviewListener);
}
