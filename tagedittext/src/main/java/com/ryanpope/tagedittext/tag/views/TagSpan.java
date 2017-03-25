package com.ryanpope.tagedittext.tag.views;

import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

public class TagSpan extends ImageSpan {
    public TagSpan(final Drawable drawable, final String source) {
        super(drawable, source);
    }
}
