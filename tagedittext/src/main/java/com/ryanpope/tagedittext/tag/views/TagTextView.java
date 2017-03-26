package com.ryanpope.tagedittext.tag.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.ryanpope.tagedittext.R;

public class TagTextView extends TextView {
    public TagTextView(final Context context, final AttributeSet attributeSet, final int defStyleAttribute) {
        super(context, attributeSet, defStyleAttribute);
        setupAttributes(context, attributeSet, defStyleAttribute);
    }

    private void setupAttributes(final Context context, final AttributeSet attributeSet, final int defStyleAttribute) {
        final TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attributeSet,
                R.styleable.TagEditText,
                defStyleAttribute,
                0);

        try {
            setDrawingCacheEnabled(true);
            setTextAppearance(typedArray);
            setPadding(typedArray);
            setBackground(typedArray);

        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            typedArray.recycle();
        }
    }

    private void setBackground(final TypedArray typedArray) {
        setBackgroundDrawable(buildDefaultBackground(typedArray));
    }

    private Drawable buildDefaultBackground(final TypedArray typedArray) {
        return new TagBackground(getContext(), typedArray);
    }

    private void setPadding(final TypedArray typedArray) {
        final int textPaddingTop = (int) typedArray.getDimension(
                R.styleable.TagEditText_textPaddingTop,
                getResources().getDimension(R.dimen.tagTextPaddingTop));

        final int textPaddingBottom = (int) typedArray.getDimension(
                R.styleable.TagEditText_textPaddingBottom,
                getResources().getDimension(R.dimen.tagTextPaddingBottom));

        final int textPaddingLeft = (int) typedArray.getDimension(
                R.styleable.TagEditText_textPaddingLeft,
                getResources().getDimension(R.dimen.tagTextPaddingLeft));

        final int textPaddingRight = (int) typedArray.getDimension(
                R.styleable.TagEditText_textPaddingRight,
                getResources().getDimension(R.dimen.tagTextPaddingRight));

        setPadding(textPaddingLeft, textPaddingTop, textPaddingRight, textPaddingBottom);
    }

    private void setTextAppearance(final TypedArray typedArray) {
        final int textAppearanceResourceId = typedArray.getResourceId(R.styleable.TagEditText_textAppearance, R.style.textAppearance);
        setTextAppearance(getContext(), textAppearanceResourceId);
    }

    @Override
    public void setText(final CharSequence text, final BufferType type) {
        super.setText(text, type);

        refreshLayout();
    }

    private void refreshLayout() {
        measure(getSpec(), getSpec());
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    private int getSpec() {
        return View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
    }
}
