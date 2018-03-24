package com.example.android.sciencequiz;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import static com.example.android.sciencequiz.Constants.*;

/**
 * This app displays a science quiz with multiple screens
 * Question 3
 */
public class Main4Activity extends AppCompatActivity {

    Button nextButton;
    int quizNumber;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    TextView textView;
    String buttonSelection;
    int correctResponse;
    int incorrectResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        //Get variables from previous activity
        Intent intent = getIntent();
        int quizNumberIntent = intent.getIntExtra(QUIZ_NUMBER_STRING, quizNumber);
        int correctResponseIntent = intent.getIntExtra(CORRECT_RESPONSE_STRING, correctResponse);
        int incorrectResponseIntent = intent.getIntExtra(INCORRECT_RESPONSE_STRING, incorrectResponse);

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
        correctResponse = getCorrectResponse();
        incorrectResponse = getIncorrectResponse();

        //Radio button 3 is the correct answer
        radioButton = (RadioButton) findViewById(R.id.radioButton3);
        //Display correct answer in this textView
        textView = (TextView) findViewById(R.id.answer);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
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
                    textView.setText(R.string.quiz3_correct);
                    correctResponse = correctResponse + 1;
                } else {
                    textView.setText(R.string.quiz3_incorrect);
                    incorrectResponse = incorrectResponse + 1;
                }
                //Display correct score
                displayScoreCounter(correctResponse, incorrectResponse);

            }

        });

        //Find the Next button and assign a listener
        Button button = findViewById(R.id.nextButton);
        button.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View view) {
                //Start new activity
                Intent intent = new Intent(Main4Activity.this, Main5Activity.class);
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
        TextView scoreTextView = findViewById(R.id.scoreCount);
        scoreTextView.setText(correctResponse + "/" + incorrectResponse);
    }

}