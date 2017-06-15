package com.example.themion.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

/**
 * Created by Themion on 2017-06-14.
 */

public class HistActivity extends Activity
{
    Intent intent;
    TextView history;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hist);
        intent = getIntent();

        history = (TextView) findViewById(R.id.history);

        String hist = intent.getStringExtra("History");

        history.setText(hist);
    }
}
