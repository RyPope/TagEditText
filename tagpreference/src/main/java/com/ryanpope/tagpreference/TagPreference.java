package com.ryanpope.tagpreference;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;

public class TagPreference extends DialogPreference {
    private final int mDialogLayoutResId = R.layout.tag_preference_dialog;

    public TagPreference(final Context context) {
        this(context, null);
    }

    public TagPreference(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagPreference(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public int getDialogLayoutResource() {
        return mDialogLayoutResId;
    }
}
