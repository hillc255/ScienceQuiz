package com.example.android.sciencequiz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.ActionBar;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends AppCompatActivity {

    ActionBar actionbar;
    TextView textview;
    LayoutParams layoutparams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //hyperlinked text
        TextView view = (TextView) findViewById(R.id.txt2);
        view.setMovementMethod(LinkMovementMethod.getInstance());

        //Find your views
        Button button = (Button) findViewById(R.id.start_button);
        //Assign a listener to your button
        button.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View view) {
                //Start your second activity
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        //close this activity when user completes quiz
        if (getIntent().getBooleanExtra("EXIT", false))
        {
            finish();
        }


    }
}

