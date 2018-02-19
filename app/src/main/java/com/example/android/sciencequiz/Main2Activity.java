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


public class Main2Activity extends AppCompatActivity {

    Button nextButton;

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    TextView textView;
    String selectWay;

    String buttonSelection;

    int correctResponse;
    int incorrectResponse;
    int quizNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        displayQuizCounter();
        displayRadioButtons();

        //find the Next button
        Button button = (Button) findViewById(R.id.nextButton);
        //assign a listener to the button
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view) {
                //Start the next activity
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                intent.putExtra("quizNumber", quizNumber);
                intent.putExtra("correctResponse", correctResponse);
                intent.putExtra("incorrectResponse", incorrectResponse);
                startActivity(intent);
            }
        });

    }

        private void displayRadioButtons(){

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
                        textView.append("Correct: This is a picture of a comet.");
                        selectWay = "Correct: This is a picture of a comet.";
                        correctResponse = correctResponse + 1;
                    }
                    else {
                        textView.append("Incorrect: This is a picture of a comet.");
                        selectWay = "Incorrect: This is a picture of a comet.";
                        incorrectResponse = incorrectResponse + 1;
                    }
                    //display correct score
                    displayScoreCounter(correctResponse, incorrectResponse);
                }

            });
        }

        private void displayQuizCounter(){
            quizNumber = quizNumber + 1;
            TextView quizTextView = (TextView) findViewById(R.id.questionNumber);
            quizTextView.setText(quizNumber + "/12");
        }

        private void displayScoreCounter(int correctResponse, int incorrectResponse){
            TextView scoreTextView = (TextView) findViewById(R.id.scoreCount);
            scoreTextView.setText(correctResponse + "/" + incorrectResponse );
     }

}
