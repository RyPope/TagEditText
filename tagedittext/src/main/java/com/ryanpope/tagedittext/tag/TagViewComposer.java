package com.ryanpope.tagedittext.tag;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.ryanpope.tagedittext.R;
import com.ryanpope.tagedittext.tag.views.TagBackground;
import com.ryanpope.tagedittext.tag.views.TagBitmap;
import com.ryanpope.tagedittext.tag.views.TagTextView;

import static android.widget.TextView.BufferType.SPANNABLE;

public class TagViewComposer {
    private final AttributeSet mAttributeSet;
    private final int mDefStyleAttribute;
    private Context mContext;

    private float mTextPaddingTop;
    private float mTextPaddingBottom;
    private float mTextPaddingLeft;
    private float mTextPaddingRight;
    private int mTextBackgroundColor;
    private int mTextColor;
    private float mTextSize;
    private float mTextBackgroundRadius;

    public TagViewComposer(final Context context, final AttributeSet attributeSet, final int defStyleAttribute) {
        mContext = context;
        mAttributeSet = attributeSet;
        mDefStyleAttribute = defStyleAttribute;

        extractAttributes(attributeSet, defStyleAttribute);
    }

    private void extractAttributes(final AttributeSet attributeSet, final int defStyleAttribute) {
        final TypedArray typedArray = mContext.obtainStyledAttributes(
                attributeSet,
                R.styleable.TagEditText,
                defStyleAttribute,
                0);

        try {
            extractPadding(typedArray);
            extractBackgroundColor(typedArray);
            extractTextColor(typedArray);
            extractBackgroundRadius(typedArray);
            extractTextSize(typedArray);

        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            typedArray.recycle();
        }
    }

    private void extractTextColor(final TypedArray typedArray) {
        mTextColor = typedArray.getColor(
                R.styleable.TagEditText_textColor,
                mContext.getResources().getColor(R.color.tagTextForegroundColor));
    }

    private void extractBackgroundRadius(final TypedArray typedArray) {
        mTextBackgroundRadius = typedArray.getDimension(
                R.styleable.TagEditText_textBackgroundRadius,
                mContext.getResources().getDimension(R.dimen.tagTextBackgroundRadius));
    }

    private void extractTextSize(final TypedArray typedArray) {
        mTextSize = typedArray.getDimension(
                R.styleable.TagEditText_textSize,
                mContext.getResources().getDimension(R.dimen.tagTextSize));
    }

    private void extractBackgroundColor(final TypedArray typedArray) {
        mTextBackgroundColor = typedArray.getColor(
                R.styleable.TagEditText_textBackgroundColor,
                mContext.getResources().getColor(R.color.tagTextBackgroundColor));
    }

    private void extractPadding(final TypedArray typedArray) {
        mTextPaddingTop = typedArray.getDimension(
                R.styleable.TagEditText_textPaddingTop,
                mContext.getResources().getDimension(R.dimen.tagTextPaddingTop));

        mTextPaddingBottom = typedArray.getDimension(
                R.styleable.TagEditText_textPaddingBottom,
                mContext.getResources().getDimension(R.dimen.tagTextPaddingBottom));

        mTextPaddingLeft = typedArray.getDimension(
                R.styleable.TagEditText_textPaddingLeft,
                mContext.getResources().getDimension(R.dimen.tagTextPaddingLeft));

        mTextPaddingRight = typedArray.getDimension(
                R.styleable.TagEditText_textPaddingRight,
                mContext.getResources().getDimension(R.dimen.tagTextPaddingRight));
    }

    public Drawable createTagSpanForTag(final Tag tag) {
        final TagTextView tagTextView = createTextView(tag.getWord());

        return buildBitmapForTextView(tagTextView);
    }

    @SuppressWarnings("deprecation")
    private TagTextView createTextView(final String text) {
        final TagTextView textView = new TagTextView(mContext, mAttributeSet, mDefStyleAttribute);
        textView.setPadding(
                (int) mTextPaddingLeft,
                (int) mTextPaddingTop,
                (int) mTextPaddingRight,
                (int) mTextPaddingBottom);

        textView.setBackgroundDrawable(buildTextViewBackground());
        textView.setTextSize(mTextSize);
        textView.setTextColor(mTextColor);
        textView.setText(text, SPANNABLE);

        return textView;
    }

    private Drawable buildTextViewBackground() {
        final TagBackground tagBackground = new TagBackground();
        tagBackground.setCornerRadius(mTextBackgroundRadius);
        tagBackground.setColor(mTextBackgroundColor);

        return tagBackground;
    }

    private Drawable buildBitmapForTextView(final TagTextView tagTextView) {
        return new TagBitmap(mContext.getResources(), tagTextView.getDrawingCache());
    }
}
