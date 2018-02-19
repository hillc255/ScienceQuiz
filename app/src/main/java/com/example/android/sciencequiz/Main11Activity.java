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


public class Main11Activity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main11);

        //psss variables from previous activity
        Intent intent = getIntent();
        int quizNumberIntent = intent.getIntExtra("quizNumber", quizNumber);
        int correctResponseIntent = intent.getIntExtra("correctResponse", correctResponse);
        int incorrectResponseIntent = intent.getIntExtra("incorrectResponse", incorrectResponse);

        //set-get the quiz number from previous activity and increase by 1 then add to textView
        setQuizNumber(quizNumberIntent);
        quizNumber = getQuizNumber();
        quizNumber = quizNumber + 1;
        TextView textView2 = (TextView) findViewById(R.id.questionNumber);
        textView2.setText(quizNumber + "/12");


        //set-get the correct and incorrect responses and add it to score textView
        setResponses(correctResponseIntent, incorrectResponseIntent);
        correctResponse = getCorrectResponse();
        incorrectResponse = getIncorrectResponse();
        TextView textView1 = (TextView) findViewById(R.id.scoreCount);
        textView1.setText(correctResponse + "/" + incorrectResponse);

        //display radio buttons
        displayRadioButtons();

        //find Next button
        Button button = (Button) findViewById(R.id.nextButton);
        //assign a listener to Next button
        button.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View view) {
                //Start new activity
                Intent intent = new Intent(Main11Activity.this, Main12Activity.class);
                intent.putExtra("quizNumber", quizNumber);
                intent.putExtra("correctResponse", correctResponse);
                intent.putExtra("incorrectResponse", incorrectResponse);
                startActivity(intent);
            }
        });

    }

    private void displayRadioButtons(){

        correctResponse = getCorrectResponse();
        incorrectResponse = getIncorrectResponse();

        //radio button 2 is the correct answer
        radioButton = (RadioButton) findViewById(R.id.radioButton2);
        //display correct answer in this textView
        textView = (TextView)findViewById(R.id.answer);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //disable all radio buttons once one is checked
                int max = group.getChildCount();
                for(int i = 0; i < max; i++){
                    group.getChildAt(i).setEnabled(false);
                }

                //Next button set invisible unless radio button is checked
                Button nextButton = (Button) findViewById(R.id.nextButton);
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    nextButton.setVisibility(View.GONE);
                }
                else{
                    nextButton.setVisibility(View.VISIBLE);
                }

                RadioButton radiobutton = (RadioButton) findViewById(checkedId);
                // Set the checked radio button background color from hex string
                radiobutton.setBackgroundColor(Color.parseColor("#0d46a0"));
                radiobutton.setTextColor(Color.parseColor("#ffffff"));

                //determine if radio button selection is correct - display correct response
                if (radioButton.isChecked()) {
                    textView.append("Correct: Uranium is needed to make nuclear energy and nuclear weapons.");
                    selectWay = "Correct: Uranium is needed to make nuclear energy and nuclear weapons.";
                    correctResponse = correctResponse  + 1;
                }
                else {
                    textView.append("Incorrect: The correct answer is \"Uranium\".");
                    selectWay = "Incorrect: The correct answer is \"Uranium\".";
                    incorrectResponse = incorrectResponse + 1;
                }

                //display correct score
                displayScoreCounter(correctResponse, incorrectResponse);

            }

        });
    }


    private void setQuizNumber(int quizNumberIntent){
        quizNumber = quizNumberIntent;
    }

    private void setResponses(int correctResponseIntent, int incorrectResponseIntent){
        correctResponse = correctResponseIntent;
        incorrectResponse = incorrectResponseIntent;
    }

    public int getQuizNumber() {
        return quizNumber;
    }

    public int getCorrectResponse() {
        return correctResponse;
    }

    public int getIncorrectResponse() {
        return incorrectResponse;
    }

    private void displayScoreCounter(int correctResponse, int incorrectResponse){
        TextView scoreTextView = (TextView) findViewById(R.id.scoreCount);
        scoreTextView.setText(correctResponse + "/" + incorrectResponse );
    }

}
