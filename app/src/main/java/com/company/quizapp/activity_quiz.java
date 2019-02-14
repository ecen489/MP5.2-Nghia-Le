package com.company.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class activity_quiz extends AppCompatActivity {
    private static final String[] quizTopics = {"Anatomy", "Geography", "Movies"};
    private String [] quizQuestionsFromTextFile = new String[3];
    private String [] quizAnswersFromTextFile = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent forwardIntent = getIntent();
        String extra = forwardIntent.getStringExtra("topic");
        TextView textview = (TextView) findViewById(R.id.questionText);

        try {
            quizQuestionsFromTextFile = getQuizQuestions();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            quizAnswersFromTextFile = getQuizAnswers();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (extra.equals(quizTopics[0])) {
            textview.setText(quizQuestionsFromTextFile[0]);
        }
        if (extra.equals(quizTopics[1])) {
            textview.setText(quizQuestionsFromTextFile[1]);
        }
        if (extra.equals(quizTopics[2])) {
            textview.setText(quizQuestionsFromTextFile[2]);
        }
    }

    public int getAnswer(String userAnswer){
        TextView textview = (TextView) findViewById(R.id.questionText);
        if (textview.getText().toString().equals(quizQuestionsFromTextFile[0])) {
            if(userAnswer.equals(quizAnswersFromTextFile[0]))
                return 1;
            else
                return 0;
        }
        else if (textview.getText().toString().equals(quizQuestionsFromTextFile[1])) {
            if(userAnswer.equals(quizAnswersFromTextFile[1]))
                return 1;
            else
                return 0;
        }
        else {
            if(userAnswer.equals(quizAnswersFromTextFile[2]))
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

    public String[] getQuizQuestions() throws IOException {
        String[] questions = new String[3];
        InputStream inputStream = getResources().openRawResource(R.raw.quizquestions);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            String line = reader.readLine();
            if(line == null) {
                break;
            }
            questions = line.split(",");
        }
        return questions;
    }

    public String[] getQuizAnswers() throws IOException {
        String[] answers = new String[3];
        InputStream inputStream = getResources().openRawResource(R.raw.quizanswers);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            String line = reader.readLine();
            if(line == null) {
                break;
            }
            answers = line.split(",");
        }
        return answers;
    }
}
