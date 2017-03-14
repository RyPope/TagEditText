package com.ryanpope.demo;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class DemoFragment extends PreferenceFragment {
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
