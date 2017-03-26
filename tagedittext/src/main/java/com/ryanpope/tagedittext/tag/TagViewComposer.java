package com.ryanpope.tagedittext.tag;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.ryanpope.tagedittext.tag.views.TagBitmap;
import com.ryanpope.tagedittext.tag.views.TagTextView;

import static android.widget.TextView.BufferType.SPANNABLE;

public class TagViewComposer {
    private final AttributeSet mAttributeSet;
    private final int mDefStyleAttribute;
    private Context mContext;

    public TagViewComposer(final Context context, final AttributeSet attributeSet, final int defStyleAttribute) {
        mContext = context;
        mAttributeSet = attributeSet;
        mDefStyleAttribute = defStyleAttribute;
    }

    public Drawable createTagSpanForTag(final Tag tag) {
        final TagTextView tagTextView = createTextView(tag.getWord());

        return buildDrawableFromTextView(tagTextView);
    }

    private TagTextView createTextView(final String text) {
        final TagTextView textView = new TagTextView(mContext, mAttributeSet, mDefStyleAttribute);
        textView.setText(text, SPANNABLE);
        return textView;
    }

    private Drawable buildDrawableFromTextView(final TagTextView tagTextView) {
        return new TagBitmap(mContext.getResources(), tagTextView.getDrawingCache());
    }
}
