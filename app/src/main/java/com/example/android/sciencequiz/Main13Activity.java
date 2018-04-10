package com.example.android.sciencequiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import static com.example.android.sciencequiz.Constants.CORRECT_RESPONSE_STRING;
import static com.example.android.sciencequiz.Constants.INCORRECT_RESPONSE_STRING;
import static com.example.android.sciencequiz.Constants.QUIZ_NUMBER_STRING;

/**
 * This app displays a science quiz with multiple screens
 * Question 12
 */
public class Main13Activity extends AppCompatActivity {

    Button nextButton, submitButton;
    int quizNumber;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    TextView textView;
    String buttonSelection;
    int correctResponse;
    int incorrectResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);

        //Get variables from previous activity
        Intent intent = getIntent();
        int quizNumberIntent = intent.getIntExtra(QUIZ_NUMBER_STRING, quizNumber);
        int correctResponseIntent = intent.getIntExtra(CORRECT_RESPONSE_STRING, correctResponse);
        int incorrectResponseIntent = intent.getIntExtra(INCORRECT_RESPONSE_STRING, incorrectResponse);

        //Set-get the quiz number from previous activity, increase by 1 then add to textView
        setQuizNumber(quizNumberIntent);
        quizNumber = getQuizNumber();
        quizNumber = quizNumber + 1;
        TextView textView2 = (TextView) findViewById(R.id.questionNumber);
        textView2.setText(quizNumber + "/12");


        //Set-get the correct and incorrect responses then add them to score in textView
        setResponses(correctResponseIntent, incorrectResponseIntent);
        correctResponse = getCorrectResponse();
        incorrectResponse = getIncorrectResponse();
        TextView textView1 = (TextView) findViewById(R.id.scoreCount);
        textView1.setText(correctResponse + "/" + incorrectResponse);

        //Set up checkboxes
        correctResponse = getCorrectResponse();
        incorrectResponse = getIncorrectResponse();

        //Initiate checkboxes and submit button
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);

        final Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setVisibility(View.GONE);

        //Create listener for submitButton
        submitButton = findViewById(R.id.buttonSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                //Display correct answer in this textView
                textView = (TextView) findViewById(R.id.answer);
                if (checkBox1.isChecked() && !checkBox2.isChecked() && !checkBox3.isChecked() && !checkBox4.isChecked()) {

                    //Set the checkbox background color from hex string
                    checkBox1.setBackgroundColor(Color.parseColor("#7e7e7e"));
                    checkBox1.setTextColor(Color.parseColor("#ffffff"));

                    checkBox1.setEnabled(false);
                    checkBox2.setEnabled(false);
                    checkBox3.setEnabled(false);
                    checkBox4.setEnabled(false);
                    submitButton.setEnabled(false);

                    //correct answer displays
                    textView.setText(R.string.quiz12_correct);
                    correctResponse = correctResponse + 1;

                    //next button becomes visible
                    nextButton.setVisibility(View.VISIBLE);

                } else {
                    //Set the checkbox background color from hex string of all checked checkboxes;
                    if (checkBox1.isChecked()) {
                        checkBox1.setBackgroundColor(Color.parseColor("#7e7e7e"));
                        checkBox1.setTextColor(Color.parseColor("#ffffff"));
                    }
                    if (checkBox2.isChecked()) {
                        checkBox2.setBackgroundColor(Color.parseColor("#7e7e7e"));
                        checkBox2.setTextColor(Color.parseColor("#ffffff"));
                    }
                    if (checkBox3.isChecked()) {
                        checkBox3.setBackgroundColor(Color.parseColor("#7e7e7e"));
                        checkBox3.setTextColor(Color.parseColor("#ffffff"));
                    }
                    if (checkBox4.isChecked()) {
                        checkBox4.setBackgroundColor(Color.parseColor("#7e7e7e"));
                        checkBox4.setTextColor(Color.parseColor("#ffffff"));
                    }
                    checkBox1.setEnabled(false);
                    checkBox2.setEnabled(false);
                    checkBox3.setEnabled(false);
                    checkBox4.setEnabled(false);
                    submitButton.setEnabled(false);

                    //incorrect answer displays
                    textView.setText(R.string.quiz12_incorrect);
                    incorrectResponse = incorrectResponse + 1;

                    //next button becomes visible
                    nextButton.setVisibility(View.VISIBLE);
                }

                //Display correct score
                displayScoreCounter(correctResponse, incorrectResponse);
            }
        });

        //Find the Next button and assign a listener
        Button button = findViewById(R.id.nextButton);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                //Start new activity
                Intent intent = new Intent(Main13Activity.this, Main14Activity.class);
                intent.putExtra(CORRECT_RESPONSE_STRING, correctResponse);
                startActivity(intent);
            }
        });

    }

    /**
     * This method sets the quiz number variable from the previous activity
     *
     * @param quizNumberIntent
     */
    private void setQuizNumber(int quizNumberIntent) {
        quizNumber = quizNumberIntent;
    }

    /**
     * This method sets the correct and incorrect responses from the previous activity
     *
     * @param correctResponseIntent
     * @param incorrectResponseIntent
     */
    private void setResponses(int correctResponseIntent, int incorrectResponseIntent) {
        correctResponse = correctResponseIntent;
        incorrectResponse = incorrectResponseIntent;
    }

    /**
     * This method gets and returns the quiz number
     *
     * @return
     */
    public int getQuizNumber() {
        return quizNumber;
    }

    /**
     * This method gets and returns the correct responses
     *
     * @return
     */
    public int getCorrectResponse() {
        return correctResponse;
    }

    /**
     * This method gets and returns the incorrect responses
     *
     * @return
     */
    public int getIncorrectResponse() {
        return incorrectResponse;
    }

    /**
     * This method displays the correct and incorrect scores
     *
     * @param correctResponse
     * @param incorrectResponse
     */
    private void displayScoreCounter(int correctResponse, int incorrectResponse) {
        TextView scoreTextView = findViewById(R.id.scoreCount);
        scoreTextView.setText(correctResponse + "/" + incorrectResponse);
    }

}