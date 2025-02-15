package com.matheusvalbert.programmercalculator.ui;

import static com.matheusvalbert.programmercalculator.ui.util.Selection.createSelection;
import static com.matheusvalbert.programmercalculator.ui.util.Util.BIN_INPUT;
import static com.matheusvalbert.programmercalculator.ui.util.Util.DEC_INPUT;
import static com.matheusvalbert.programmercalculator.ui.util.Util.HEX_INPUT;
import static com.matheusvalbert.programmercalculator.ui.util.Util.OCT_INPUT;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.Task;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.matheusvalbert.programmercalculator.R;
import com.matheusvalbert.programmercalculator.core.domain.History;
import com.matheusvalbert.programmercalculator.databinding.ActivityMainBinding;
import com.matheusvalbert.programmercalculator.ui.components.AutoFitEditText;
import com.matheusvalbert.programmercalculator.ui.expression.ExpressionViewModel;
import com.matheusvalbert.programmercalculator.ui.expression.state.ExpressionState;
import com.matheusvalbert.programmercalculator.ui.factory.ViewModelFactory;
import com.matheusvalbert.programmercalculator.ui.history.list.HistoryAdapter;
import com.matheusvalbert.programmercalculator.ui.history.HistoryViewModel;
import com.matheusvalbert.programmercalculator.ui.history.state.HistoryState;
import com.matheusvalbert.programmercalculator.ui.listener.OnClickListener;
import com.matheusvalbert.programmercalculator.ui.util.AnimationUtil;
import com.matheusvalbert.programmercalculator.core.util.Base;
import com.matheusvalbert.programmercalculator.ui.util.Constants;
import com.matheusvalbert.programmercalculator.ui.util.StateUtil;
import com.matheusvalbert.programmercalculator.ui.util.Util;

import java.io.InvalidClassException;

public class MainActivity extends AppCompatActivity {

    private ClipboardManager mClipboard;
    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseCrashlytics mFirebaseCrashlytics;
    private ActivityMainBinding mBinding;
    private ExpressionViewModel mExpressionViewModel;
    private HistoryViewModel mHistoryViewModel;
    private HistoryAdapter mHistoryAdapter;

    private int mScreenHeight;
    private ObjectAnimator mBaseAnimator;
    private AnimatorSet mShowHistory;
    private AnimatorSet mShowKeyboard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
        mFirebaseCrashlytics = FirebaseCrashlytics.getInstance();

        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mClipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        mScreenHeight = getResources().getDisplayMetrics().heightPixels;
        mBinding.expression.setKeyFilter(HEX_INPUT);

        initializeViewModel();
        initializeExpressionInput();
        initializeAnimation();
        initializeBaseButton();
        initializeAdapter();
        initializeViewChangeButtons();
        initializeObserver();
        initializeBackPressed();

        expressionChangeListener();

        Util.mapKeyboard(mBinding.viewKeyboard, mBinding.expression, mExpressionViewModel);
    }

    private void initializeViewModel() {
        try {
            mExpressionViewModel = new ViewModelProvider(
                this,
                ViewModelProvider.Factory.from(ViewModelFactory.createViewModel(ExpressionViewModel.class))
            ).get(ExpressionViewModel.class);

            mHistoryViewModel = new ViewModelProvider(
                this,
                ViewModelProvider.Factory.from(ViewModelFactory.createViewModel(HistoryViewModel.class))
            ).get(HistoryViewModel.class);
        } catch (InvalidClassException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeExpressionInput() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void initializeAnimation() {
        mBaseAnimator = AnimationUtil.changeBase(mBinding.baseIndicator);

        mShowHistory = AnimationUtil.
                changeView(mBinding.viewKeyboard.getRoot(), mBinding.viewHistory.getRoot());

        mShowKeyboard = AnimationUtil.
                changeView(mBinding.viewHistory.getRoot(), mBinding.viewKeyboard.getRoot());
    }

    private void initializeBaseButton() {
        mBinding.hexadecimalResult.setOnClickListener(v -> {
            changeBase(Base.HEX);
            mBinding.expression.setKeyFilter(HEX_INPUT);
        });
        mBinding.decimalResult.setOnClickListener(v -> {
            changeBase(Base.DEC);
            mBinding.expression.setKeyFilter(DEC_INPUT);
        });
        mBinding.octalResult.setOnClickListener(v -> {
            changeBase(Base.OCT);
            mBinding.expression.setKeyFilter(OCT_INPUT);
        });
        mBinding.binaryResult.setOnClickListener(v -> {
            changeBase(Base.BIN);
            mBinding.expression.setKeyFilter(BIN_INPUT);
        });

        mBinding.hexadecimalResult.setOnLongClickListener(v -> {
            copyToClipboard(Base.HEX);
            return true;
        });

        mBinding.decimalResult.setOnLongClickListener(v -> {
            copyToClipboard(Base.DEC);
            return true;
        });

        mBinding.octalResult.setOnLongClickListener(v -> {
            copyToClipboard(Base.OCT);
            return true;
        });

        mBinding.binaryResult.setOnLongClickListener(v -> {
            copyToClipboard(Base.BIN);
            return true;
        });
    }

    private void copyToClipboard(@Base int base) {
        String copiedToClipboard = getString(R.string.copied_to_clipboard);
        String baseString = getString(Util.getBaseString(base));
        ClipData clipData = ClipData.newPlainText(baseString + Constants.SPACE +
                copiedToClipboard, mBinding.hexadecimalResultValue.getText());
        mClipboard.setPrimaryClip(clipData);
    }

    private void changeBase(@Base int base) {
        mExpressionViewModel.updateBase(base);
        animateBaseTransition(base);
        StateUtil.updateButtonState(mBinding, base);
    }

    private void expressionChangeListener() {
        mBinding.expression.addListener(new AutoFitEditText.Listener() {
            @Override
            public void onPaste(String text) {
                mExpressionViewModel.newInput(text, createSelection(mBinding.expression));
            }

            @Override
            public void onKeyDown(int keyCode) {
                mExpressionViewModel
                        .newInput(Util.getInput(keyCode), createSelection(mBinding.expression));
            }

            @Override
            public void onEqual() {
                mExpressionViewModel.onEqual();
            }
        });
    }

    private void animateBaseTransition(@Base int base) {
        float currentY = mBinding.baseIndicator.getTranslationY();
        mBaseAnimator.setFloatValues(currentY, AnimationUtil.getYDelta(mScreenHeight, base));
        mBaseAnimator.start();
    }

    private void initializeAdapter() {
        mHistoryAdapter = new HistoryAdapter(new OnClickListener() {
            @Override
            public void onClick(History history) {
                mExpressionViewModel.newInputFromHistory(history);
                animateBaseTransition(history.getBase());
                StateUtil.updateButtonState(mBinding, history.getBase());
                mShowKeyboard.start();
            }

            @Override
            public void onLongClick(History history) {
                ClipData clipData = ClipData.newPlainText("Result " +
                    getString(R.string.copied_to_clipboard), history.getExpression() + " = " +
                    history.getResult() + " (" + getString(Util.getBaseString(history.getBase())) + ")");
                mClipboard.setPrimaryClip(clipData);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        mBinding.viewHistory.historyRecyclerView.setLayoutManager(linearLayoutManager);
        mBinding.viewHistory.historyRecyclerView.setAdapter(mHistoryAdapter);
    }

    private void initializeViewChangeButtons() {
        mBinding.viewKeyboard.history.setOnClickListener(v -> {
            mHistoryViewModel.updateHistory();
            mShowHistory.start();
        });
        mBinding.viewHistory.back.setOnClickListener(v -> mShowKeyboard.start());
        mBinding.viewHistory.delete.setOnClickListener(v -> {
            mHistoryViewModel.deleteHistory();
            mShowKeyboard.start();
        });
    }

    private void initializeObserver() {
        mExpressionViewModel.stateOnceAndStream().observe(this, state -> {
            ExpressionState expressionState = (ExpressionState) state;
            int base = expressionState.getBase();
            mBinding.baseIndicator.setTranslationY(AnimationUtil.getYDelta(mScreenHeight, base));
            mBinding.expression.setText(expressionState.getExpression());
            mBinding.expression.setSelection(expressionState.getCursorPosition());
            updateResults(expressionState);
        });

        mExpressionViewModel.updateRequestReviewListener(() -> {
            ReviewManager manager = ReviewManagerFactory.create(this);
            Task<ReviewInfo> request = manager.requestReviewFlow();
            request.addOnCompleteListener(requestInfoResult -> {
                if (requestInfoResult.isSuccessful()) {
                    Task<Void> flow = manager.launchReviewFlow(this, requestInfoResult.getResult());
                    flow.addOnCompleteListener(task -> {
                        if (!task.isSuccessful()) {
                            if (task.getException() == null) return;
                            mFirebaseCrashlytics.recordException(task.getException());
                        }
                    });
                } else {
                    if (requestInfoResult.getException() == null) return;
                    mFirebaseCrashlytics.recordException(requestInfoResult.getException());
                }
            });
        });

        mExpressionViewModel.updateHistoryListener(history -> mHistoryViewModel.addNewHistory(history));

        mHistoryViewModel.stateOnceAndStream().observe(this, state -> {
            HistoryState historyState = (HistoryState) state;
            mBinding.viewKeyboard.history.setEnabled(historyState.getHistoryAvailability());
            mHistoryAdapter.updateHistory(historyState.getHistoryItems());
        });
    }

    private void updateResults(ExpressionState expressionState) {
        mBinding.hexadecimalResultValue.setText(expressionState.getResult(Base.HEX));
        mBinding.decimalResultValue.setText(expressionState.getResult(Base.DEC));
        mBinding.octalResultValue.setText(expressionState.getResult(Base.OCT));
        mBinding.binaryResultValue.setText(expressionState.getResult(Base.BIN));
    }

    private void initializeBackPressed() {
        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (mBinding.viewHistory.getRoot().getVisibility() == View.VISIBLE) {
                    mShowKeyboard.start();
                    return;
                }
                moveTaskToBack(true);
            }
        });
    }
}
