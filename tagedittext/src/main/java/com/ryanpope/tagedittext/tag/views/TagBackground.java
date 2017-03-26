package com.ryanpope.tagedittext.tag.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;

import com.ryanpope.tagedittext.R;

public class TagBackground extends GradientDrawable {
    private final Context mContext;

    public TagBackground(final Context context, final TypedArray typedArray) {
        mContext = context;
        init(typedArray);
    }

    private void init(final TypedArray typedArray) {
        setCornerRadius(typedArray);
        setColor(typedArray);
        setShape(RECTANGLE);
    }

    private void setColor(final TypedArray typedArray) {
        final int textBackgroundColor = typedArray.getColor(
                R.styleable.TagEditText_textBackgroundColor,
                mContext.getResources().getColor(R.color.tagTextBackgroundColor));

        setColor(textBackgroundColor);
    }

    private void setCornerRadius(final TypedArray typedArray) {
        final float textBackgroundRadius = typedArray.getDimension(
                R.styleable.TagEditText_textBackgroundRadius,
                mContext.getResources().getDimension(R.dimen.tagTextBackgroundRadius));

        setCornerRadius(textBackgroundRadius);
    }
}
