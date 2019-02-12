package com.company.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String[] quizTopics = {"Anatomy", "Geography", "Movies"};
    private static final int req_code = 3500;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent forwardIntent = new Intent(this, activity_quiz.class);

        ListView simpleList = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quizTopics);
        simpleList.setAdapter(myAdapter);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String topic = parent.getItemAtPosition(position).toString();
                forwardIntent.putExtra("topic", topic);

                // return 1 or 0 for result of quiz question
                startActivityForResult(forwardIntent, req_code);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        TextView scoreText = (TextView) findViewById(R.id.scoreBoard);
        if(requestCode==req_code){
            int answer = intent.getIntExtra("isCorrect", 0);
            if(answer == 1){
                score += 100;
                scoreText.setText("Score:" + score);
                Toast.makeText(MainActivity.this, "Correct! +100 Points.", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(MainActivity.this, "Incorrect! +0 Points.", Toast.LENGTH_SHORT).show();
        }
    }

    public void resetClick(View view){
        TextView scoreText = (TextView) findViewById(R.id.scoreBoard);
        score = 0;
        scoreText.setText("Score:" + score);
    }
}
