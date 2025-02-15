package com.matheusvalbert.programmercalculator.ui.components;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import java.util.Set;

public class AutoFitEditText extends AppCompatEditText {

    private final SparseIntArray mTextCachedSizes = new SparseIntArray();
    private final SizeTester mSizeTester;
    private ActionMode mActivationMode;
    private final int mMinTextSize;

    private float mMaxTextSize;
    private final boolean mInitialized;
    private TextPaint mPaint;
    private Listener mListener;
    private Set<Integer> mKeyFilter;

    public AutoFitEditText(final Context context) {
        this(context, null, 0);
    }

    public AutoFitEditText(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoFitEditText(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        mMinTextSize = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());
        mMaxTextSize = getTextSize();
        mSizeTester = (suggestedSize, availableSPace) -> {
            final RectF textRect = new RectF();
            mPaint.setTextSize(suggestedSize);
            final String text = getText().toString();
            textRect.bottom = mPaint.getFontSpacing();
            textRect.right = mPaint.measureText(text);
            textRect.offsetTo(0, 0);
            return !availableSPace.contains(textRect);
        };

        mInitialized = true;

        this.requestFocus();
        setShowSoftInputOnFocus(false);
    }

    @Override
    public void setTypeface(final Typeface tf) {
        if (mPaint == null) mPaint = new TextPaint(getPaint());
        mPaint.setTypeface(tf);
        super.setTypeface(tf);
    }

    @Override
    public void setTextSize(final float size) {
        mMaxTextSize = size;
        mTextCachedSizes.clear();
        adjustTextSize();
    }

    @Override
    public void setTextSize(final int unit, final float size) {
        final Context c = getContext();
        Resources r;
        if (c == null) r = Resources.getSystem();
        else r = c.getResources();
        mMaxTextSize = TypedValue.applyDimension(unit, size, r.getDisplayMetrics());
        mTextCachedSizes.clear();
        adjustTextSize();
    }

    private void adjustTextSize() {
        if (!mInitialized) return;
        final int heightLimit = getMeasuredHeight() - getCompoundPaddingBottom() - getCompoundPaddingTop();
        final RectF availableSpaceRect = new RectF();
        availableSpaceRect.right = Float.MAX_VALUE;
        availableSpaceRect.bottom = heightLimit;
        super.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                efficientTextSizeSearch(mMinTextSize, (int) mMaxTextSize, mSizeTester, availableSpaceRect));
    }

    private int efficientTextSizeSearch(final int start, final int end,
                                        final SizeTester sizeTester, final RectF availableSpace) {
        final String text = getText().toString();
        final int key = text.length();
        int size = mTextCachedSizes.get(key);
        if (size != 0) return size;
        size = binarySearch(start, end, sizeTester, availableSpace);
        mTextCachedSizes.put(key, size);
        return size;
    }

    private int binarySearch(final int start, final int end,
                             final SizeTester sizeTester, final RectF availableSpace) {
        int lastBest = start;
        int lo = start;
        int hi = end - 1;
        int mid;
        while (lo <= hi) {
            mid = lo + hi >>> 1;
            final boolean midValCmp = sizeTester.onTestSize(mid, availableSpace);
            if (midValCmp) {
                hi = mid - 1;
                lastBest = hi;
            } else {
                lastBest = lo;
                lo = mid + 1;
            }
        }
        return lastBest;
    }

    @Override
    protected void onSizeChanged(final int width, final int height, final int oldWidth, final int oldHeight) {
        mTextCachedSizes.clear();
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        if (height != oldHeight) adjustTextSize();
    }

    @Override
    public ActionMode startActionMode(ActionMode.Callback callback, int type) {
        mActivationMode = super.startActionMode(callback, type);
        return mActivationMode;
    }

    @Nullable
    @Override
    public InputConnection onCreateInputConnection(@NonNull EditorInfo outAttrs) {
        return null;
    }

    @Override
    public boolean onTextContextMenuItem(int id) {
        if (id == android.R.id.paste) {
            ClipboardManager clipboard =
                    (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard != null && clipboard.hasPrimaryClip()) {
                ClipData clipData = clipboard.getPrimaryClip();
                if (clipData != null && clipData.getItemCount() > 0) {
                    CharSequence pastedText = clipData.getItemAt(0).getText();
                    mListener.onPaste(pastedText.toString());
                }
            }
            mActivationMode.finish();
            return true;
        }

        return super.onTextContextMenuItem(id);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mKeyFilter.contains(keyCode)) {
            if (event.isShiftPressed()) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_8 -> keyCode = KeyEvent.KEYCODE_STAR;
                    case KeyEvent.KEYCODE_9 -> keyCode = KeyEvent.KEYCODE_NUMPAD_LEFT_PAREN;
                    case KeyEvent.KEYCODE_0 -> keyCode = KeyEvent.KEYCODE_NUMPAD_RIGHT_PAREN;
                    case KeyEvent.KEYCODE_EQUALS -> keyCode = KeyEvent.KEYCODE_PLUS;
                }
            } else if (keyCode == KeyEvent.KEYCODE_EQUALS && !event.isShiftPressed()) {
                mListener.onEqual();
                return true;
            }
            mListener.onKeyDown(keyCode);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            return super.onKeyDown(keyCode, event);
        }
        return false;
    }

    public void addListener(Listener listener) {
        mListener = listener;
    }

    public void setKeyFilter(Set<Integer> keyFilter) {
        mKeyFilter = keyFilter;
    }

    private interface SizeTester {
        boolean onTestSize(int suggestedSize, RectF availableSpace);
    }

    public interface Listener {
        void onPaste(String text);
        void onKeyDown(int keyCode);
        void onEqual();
    }
}
