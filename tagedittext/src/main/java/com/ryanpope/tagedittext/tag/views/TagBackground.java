package com.ryanpope.tagedittext.tag.views;

import android.graphics.drawable.GradientDrawable;

public class TagBackground extends GradientDrawable {
    public TagBackground() {
        init();
    }

    private void init() {
        setShape(RECTANGLE);
    }
}
