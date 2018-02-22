package com.example.android.sciencequiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * This app displays a science quiz with multiple screens
 * Question 2
 */
public class Main3Activity extends AppCompatActivity {

    Button nextButton;
    int quizNumber;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    TextView textView;
    String selectWay;
    String buttonSelection;
    int correctResponse;
    int incorrectResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //Get variables from previous activity
        Intent intent = getIntent();
        int quizNumberIntent = intent.getIntExtra("quizNumber", quizNumber);
        int correctResponseIntent = intent.getIntExtra("correctResponse", correctResponse);
        int incorrectResponseIntent = intent.getIntExtra("incorrectResponse", incorrectResponse);

        //Set-get the quiz number from previous activity, increase by 1 then add to textView
        setQuizNumber(quizNumberIntent);
        quizNumber = getQuizNumber();
        quizNumber = quizNumber + 1;
        TextView textView2 = findViewById(R.id.questionNumber);
        textView2.setText(quizNumber + "/12");

        //Set-get the correct and incorrect responses then add them to score in textView
        setResponses(correctResponseIntent, incorrectResponseIntent);
        correctResponse = getCorrectResponse();
        incorrectResponse = getIncorrectResponse();
        TextView textView1 = findViewById(R.id.scoreCount);
        textView1.setText(correctResponse + "/" + incorrectResponse);

        //Set up radio buttons
        displayRadioButtons();

        //Find the Next button and assign a listener
        Button button = findViewById(R.id.nextButton);
        button.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View view) {
                //Start the next activity and pass the variables
                Intent intent = new Intent(Main3Activity.this, Main4Activity.class);
                intent.putExtra("quizNumber", quizNumber);
                intent.putExtra("correctResponse", correctResponse);
                intent.putExtra("incorrectResponse", incorrectResponse);
                startActivity(intent);
            }
        });
    }

    /**
     * This method sets up radio button group
     */
    private void displayRadioButtons() {

        correctResponse = getCorrectResponse();
        incorrectResponse = getIncorrectResponse();

        //Radio button 1 is the correct answer
        radioButton = findViewById(R.id.radioButton1);
        //Display correct answer in this textView
        textView = findViewById(R.id.answer);

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //Disable all radio buttons once one is checked
                int max = group.getChildCount();
                for (int i = 0; i < max; i++) {
                    group.getChildAt(i).setEnabled(false);
                }

                //Next button set invisible unless radio button is checked
                Button nextButton = findViewById(R.id.nextButton);
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    nextButton.setVisibility(View.GONE);
                } else {
                    nextButton.setVisibility(View.VISIBLE);
                }

                RadioButton radiobutton = findViewById(checkedId);
                // Set the checked radio button background color from hex string
                radiobutton.setBackgroundColor(Color.parseColor("#7e7e7e"));
                radiobutton.setTextColor(Color.parseColor("#ffffff"));

                //Determine if radio button selection is correct or incorrect - display response
                if (radioButton.isChecked()) {
                    textView.append("Correct: Radio waves.");
                    selectWay = "Correct: Radio waves.";
                    correctResponse = correctResponse + 1;
                } else {
                    textView.append("Incorrect: Radio waves is the correct answer.");
                    selectWay = "Incorrect: Radio waves is the correct answer.";
                    incorrectResponse = incorrectResponse + 1;
                }

                //Display correct score
                displayScoreCounter(correctResponse, incorrectResponse);

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
