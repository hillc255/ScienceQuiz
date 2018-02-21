package com.example.android.sciencequiz;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.app.PendingIntent.getActivity;


/**
 * This app displays a science quiz with multiple screens
 * Splash page
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Get the hyperlinked text
        TextView view = findViewById(R.id.txt2);
        view.setMovementMethod(LinkMovementMethod.getInstance());

        //Find the button and assign a listener
        Button button = findViewById(R.id.start_button);
        button.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View view) {
                //Start the quiz with the second activity
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        //Close the application
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

    }
}

