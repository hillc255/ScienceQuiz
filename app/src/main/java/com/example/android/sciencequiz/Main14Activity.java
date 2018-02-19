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


public class Main14Activity extends AppCompatActivity {

    Button nextButton;
    TextView textView;
    int correctResponse;
    String finalScoreCount;
    String name = "Claudia";
    boolean bExit;
    boolean bStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);

        //psss variables from previous activity
        Intent intent = getIntent();
        int correctResponseIntent = intent.getIntExtra("correctResponse", correctResponse);

        //set-get the correct and incorrect responses and add it to score textView
        setCorrectResponse(correctResponseIntent);
        correctResponse = getCorrectResponse();

        finalScoreCount = correctResponse + "/12";
        TextView textView1 = (TextView) findViewById(R.id.scoreCount);
        textView1.setText(finalScoreCount);
        int checkSelect;

        //user wants to start quiz again - change button name to start
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox_id1);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Button buttonCheck = (Button) findViewById(R.id.clickButton);
                    if(isChecked) {
                        buttonCheck.setText("Start");
                    }
                    else if(!isChecked){
                        buttonCheck.setText("End");
                    }
            }
        });

        //find End button
        Button button = (Button) findViewById(R.id.clickButton);
        //assign a listener to End button
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //check box to take quiz again
                final CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkbox_id1);

                //case 1: Begin quiz again
                if (checkBox1.isChecked()){
                  startApp();
                }

                //case 2: exit program with goodbye toast
                else if (!checkBox1.isChecked()){
                    //display good-by and close application
                    exitApp();
                }
            }
        });
     }

    private void setCorrectResponse(int correctResponseIntent) {
        correctResponse = correctResponseIntent;
    }

    public int getCorrectResponse() {

        return correctResponse;
    }


    private void startApp(){
        Intent intent = new Intent(Main14Activity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", false);
        startActivity(intent);
    }

    private void exitApp(){
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