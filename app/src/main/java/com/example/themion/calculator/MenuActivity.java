package com.example.themion.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;

public class MenuActivity extends Activity
{
    Button link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        link = (Button) findViewById(R.id.btn_link);
    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_link:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mathway.com/ko/FiniteMath"));
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                startActivity(intent);

                break;

            case R.id.btn_back:
                finish();

                break;
        }
    }
}
