package com.ryanpope.tagedittext.tag.views;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

public class TagBitmap extends BitmapDrawable {
    public TagBitmap(final Resources res, final Bitmap bitmap) {
        super(res, bitmap);

        setBounds(0, 0, getIntrinsicWidth(), getIntrinsicHeight());
    }
}
