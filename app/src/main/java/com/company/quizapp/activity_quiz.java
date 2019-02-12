package com.company.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class activity_quiz extends AppCompatActivity {
    private static final String[] quizTopics = {"Anatomy", "Geography", "Movies"};
    private static final String[] quizQuestions = {"What is the largest joint in the human body?",
            "What is the world's longest river?",
            "Who directed the Lord of the Rings trilogy?"};
    private static final String[] quizAnswers = {"knee", "amazon", "peter jackson"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent forwardIntent = getIntent();
        String extra = forwardIntent.getStringExtra("topic");
        TextView textview = (TextView) findViewById(R.id.questionText);

        if (extra.equals(quizTopics[0])) {
            textview.setText(quizQuestions[0]);
        }
        if (extra.equals(quizTopics[1])) {
            textview.setText(quizQuestions[1]);
        }
        if (extra.equals(quizTopics[2])) {
            textview.setText(quizQuestions[2]);
        }
    }

    public int getAnswer(String userAnswer){
        TextView textview = (TextView) findViewById(R.id.questionText);
        if (textview.getText().toString().equals(quizQuestions[0])) {
            if(userAnswer.equals(quizAnswers[0]))
                return 1;
            else
                return 0;
        }
        else if (textview.getText().toString().equals(quizQuestions[1])) {
            if(userAnswer.equals(quizAnswers[1]))
                return 1;
            else
                return 0;
        }
        else {
            if(userAnswer.equals(quizAnswers[2]))
                return 1;
            else
                return 0;
        }
    }

    public void submitClick (View view) {
        Intent backIntent = new Intent();
        EditText ansText = (EditText) findViewById(R.id.myAns);
        int isCorrect;

        isCorrect = getAnswer(ansText.getText().toString().toLowerCase());
        backIntent.putExtra("isCorrect", isCorrect);
        setResult(RESULT_OK, backIntent);
        finish();
    }
}
