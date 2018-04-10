package com.example.android.sciencequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.android.sciencequiz.Constants.CORRECT_RESPONSE_STRING;
import static com.example.android.sciencequiz.Constants.INCORRECT_RESPONSE_STRING;
import static com.example.android.sciencequiz.Constants.QUIZ_NUMBER_STRING;

/**
 * This app displays a science quiz with multiple screens
 * Question 7
 */
public class Main8Activity extends AppCompatActivity {

    Button submitButton, nextButton;
    int quizNumber;
    TextView textView;
    String buttonSelection;
    int correctResponse;
    int incorrectResponse;

    private EditText edittext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

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

        //Set up responses
        correctResponse = getCorrectResponse();
        incorrectResponse = getIncorrectResponse();

        //Display correct answer in this textView
        textView = (TextView) findViewById(R.id.answer);

        edittext = (EditText) findViewById(R.id.editTextBox);
        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Set-up Next button to be disabled if EditText has been submitted
                nextButton = (Button) findViewById(R.id.nextButton);

                final EditText numString = (EditText) findViewById(R.id.editTextBox);
                String str = numString.getText().toString();

                //Determine if input is correct - display correct response
                if (str.equals("3")) {
                    textView.setText(R.string.quiz7_correct);
                    correctResponse = correctResponse + 1;
                    submitButton.setEnabled(false);
                    nextButton.setVisibility(View.VISIBLE);

                } else if (TextUtils.isEmpty(str) || !str.equals("3")) {
                    textView.setText(R.string.quiz7_incorrect);
                    incorrectResponse = incorrectResponse + 1;
                    submitButton.setEnabled(false);
                    nextButton.setVisibility(View.VISIBLE);
                }

                //Display correct score
                displayScoreCounter(correctResponse, incorrectResponse);
            }

        });


        //Find the Next button and assign a listener
        Button button = (Button) findViewById(R.id.nextButton);
        button.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View view) {
                //Start new activity
                Intent intent = new Intent(Main8Activity.this, Main9Activity.class);
                intent.putExtra(QUIZ_NUMBER_STRING, quizNumber);
                intent.putExtra(CORRECT_RESPONSE_STRING, correctResponse);
                intent.putExtra(INCORRECT_RESPONSE_STRING, incorrectResponse);
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
        TextView scoreTextView = (TextView) findViewById(R.id.scoreCount);
        scoreTextView.setText(correctResponse + "/" + incorrectResponse);
    }

}
