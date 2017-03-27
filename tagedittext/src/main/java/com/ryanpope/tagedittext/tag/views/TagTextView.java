package com.ryanpope.tagedittext.tag.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class TagTextView extends TextView {
    public TagTextView(final Context context, final AttributeSet attributeSet, final int defStyleAttribute) {
        super(context, attributeSet, defStyleAttribute);
        setupAttributes();
    }

    private void setupAttributes() {
        setDrawingCacheEnabled(true);
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
