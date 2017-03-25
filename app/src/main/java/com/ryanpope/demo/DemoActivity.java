package com.ryanpope.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class DemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_layout);

        final EditText editText = (EditText) findViewById(R.id.edit_text);
        editText.setText("Test1 Test2");
    }
}
