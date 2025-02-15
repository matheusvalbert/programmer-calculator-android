package com.matheusvalbert.programmercalculator.ui.base;

@FunctionalInterface
public interface ErrorHandler {
    void handle(Exception e);
}
