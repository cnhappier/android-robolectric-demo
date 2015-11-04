package com.example.sample;

import android.app.Activity;
import android.os.Bundle;

import com.example.library.TestUtils;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TestUtils.hello();
    }
}