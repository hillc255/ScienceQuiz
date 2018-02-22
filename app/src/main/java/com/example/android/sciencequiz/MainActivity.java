package com.example.android.sciencequiz;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * This app displays a science quiz with multiple screens
 * Splash page
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

