package com.ryanpope.tagedittext.tag;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

class TagEditTextTestActivity extends Activity {
    public static final int TEST_LAYOUT_ID = 1;
    public static final int TEST_EDIT_TEXT_ID = 2;

    @SuppressWarnings("ResourceType")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout view = new LinearLayout(this);
        view.setId(TEST_LAYOUT_ID);

        setContentView(view);
    }
}