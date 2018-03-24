package com.example.android.sciencequiz;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.sciencequiz.Constants.CORRECT_RESPONSE_STRING;

/**
 * Summary screen in the Science Quiz
 */

public class Main14Activity extends AppCompatActivity {

    Button nextButton;
    TextView textView;
    int correctResponse;
    String finalScoreCount;
    boolean bExit;
    boolean bStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);

        //Get variables from previous activity//psss variables from previous activity
        Intent intent = getIntent();
        int correctResponseIntent = intent.getIntExtra(CORRECT_RESPONSE_STRING, correctResponse);

        //Set-get the correct and incorrect responses and add it to score textView
        setCorrectResponse(correctResponseIntent);
        correctResponse = getCorrectResponse();

        finalScoreCount = correctResponse + "/12";
        TextView textView1 = (TextView) findViewById(R.id.scoreCount);
        textView1.setText(finalScoreCount);
        int checkSelect;

        //Checkbox if user wants to start quiz again - change button name to Start
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox_id1);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Button buttonCheck = (Button) findViewById(R.id.clickButton);
                if (isChecked) {
                    buttonCheck.setText("Start");
                } else if (!isChecked) {
                    buttonCheck.setText("End");
                }
            }
        });

        //Find the End/Start button and assign a listener
        Button button = (Button) findViewById(R.id.clickButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //Checkbox to take quiz again
                final CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkbox_id1);

                //Case 1: Begin quiz again
                if (checkBox1.isChecked()) {
                    startApp();
                }

                //Case 2: Exit program with goodbye toast
                else if (!checkBox1.isChecked()) {
                    //Display goodbye and close application
                    exitApp();
                }
            }
        });
    }

    /**
     * This method sets the correct response variable passed from previous activity
     *
     * @param correctResponseIntent
     */
    private void setCorrectResponse(int correctResponseIntent) {
        correctResponse = correctResponseIntent;
    }

    /**
     * This method gets the correct response
     *
     * @return
     */
    public int getCorrectResponse() {

        return correctResponse;
    }

    /**
     * This method starts the Science Quiz again
     */
    private void startApp() {
        Intent intent = new Intent(Main14Activity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", false);
        startActivity(intent);
    }

    /**
     * This method exits the application with a goodbye toast
     */
    private void exitApp() {
        Toast toast = Toast.makeText(getApplicationContext(), "Goodbye", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Main14Activity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            }
        }, 1500);
    }

}