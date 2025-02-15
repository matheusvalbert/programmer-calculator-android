package com.matheusvalbert.programmercalculator.ui.base;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.crashlytics.FirebaseCrashlytics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public abstract class BaseViewModel extends ViewModel {
    private final String TAG = this.getClass().getSimpleName();

    private final ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    private final List<Future<?>> mPendingTasks = new ArrayList<>();

    private final FirebaseCrashlytics mFirebaseCrashlytics;

    protected final MutableLiveData<State> mUiState;

    protected BaseViewModel(State state) {
        mUiState = new MutableLiveData<>(state);
        mFirebaseCrashlytics = FirebaseCrashlytics.getInstance();
    }

    protected void async(Runnable task) {
        async(task, _ -> {});
    }

    protected void async(Runnable task, ErrorHandler handler) {
        Future<?> future = mExecutor.submit(() -> {
            try {
                task.run();
            } catch (Exception e) {
                handler.handle(e);
                Log.e(TAG, "Error during async execution", e);
                mFirebaseCrashlytics.recordException(e);
            }
        });
        mPendingTasks.add(future);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        for (Future<?> task: mPendingTasks) {
            task.cancel(true);
        }
        mExecutor.shutdown();
    }

    public LiveData<State> stateOnceAndStream() {
        return mUiState;
    }
}
