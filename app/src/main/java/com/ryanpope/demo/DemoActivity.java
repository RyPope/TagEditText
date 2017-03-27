package com.ryanpope.demo;

import android.app.Activity;
import android.os.Bundle;

import com.ryanpope.tagedittext.TagEditText;

public class DemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_layout);

        final TagEditText editText = (TagEditText) findViewById(R.id.edit_text);
        editText.setText("This is a basic demo of the word tags android library.");
    }
}
