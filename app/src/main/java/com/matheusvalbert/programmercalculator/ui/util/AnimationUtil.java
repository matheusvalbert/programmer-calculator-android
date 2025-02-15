package com.matheusvalbert.programmercalculator.ui.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.matheusvalbert.programmercalculator.core.util.Base;

public class AnimationUtil {
    private static final int DELAY = 50;
    private static final int DURATION = 300;

    public static ObjectAnimator changeBase(View baseIndicator) {
        ObjectAnimator baseAnimator = new ObjectAnimator();
        baseAnimator.setDuration(DURATION);
        baseAnimator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            baseIndicator.setTranslationY(value);
        });
        return baseAnimator;
    }

    public static AnimatorSet changeView(ConstraintLayout disappear, ConstraintLayout appear) {
        ObjectAnimator translateDown = ObjectAnimator.ofFloat(disappear, "translationY", 0f, 500f);
        ObjectAnimator translateUp = ObjectAnimator.ofFloat(appear, "translationY", 500f, 0f);
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(disappear, "alpha", 1f, 0f);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(appear, "alpha", 0f, 1f);

        AnimatorSet animatorOut = new AnimatorSet();
        animatorOut.playTogether(translateDown, fadeOut);
        animatorOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                disappear.setVisibility(View.GONE);
            }
        });

        AnimatorSet animatorIn = new AnimatorSet();
        animatorIn.playTogether(translateUp, fadeIn);
        animatorIn.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                appear.setVisibility(View.VISIBLE);
            }
        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setStartDelay(DELAY);
        animatorSet.setDuration(DURATION);
        animatorSet.playSequentially(animatorOut, animatorIn);

        return animatorSet;
    }

    public static float getYDelta(float screenHeight, @Base int base) {
        switch (base) {
            case Base.HEX -> {
                return 0f;
            }
            case Base.DEC -> {
                return screenHeight * 0.05f;
            }
            case Base.OCT -> {
                return screenHeight * 0.1f;
            }
            case Base.BIN -> {
                return screenHeight * 0.15f;
            }
            default -> throw new IllegalArgumentException("Valid base is required");
        }
    }
}
