package quizme.olgamrost.com.quizme.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import quizme.olgamrost.com.quizme.R;

public class MainActivity extends AppCompatActivity {

    int numberOfQuestionsAnswered, numberOfCorrectAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        numberOfQuestionsAnswered = 0;
        numberOfCorrectAnswers = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showQuestions(final View cmd){

        final Intent intent = new Intent(this, ShowQuizActivity.class);
        intent.putExtra("number", numberOfQuestionsAnswered);
        intent.putExtra("correctAnswers", numberOfCorrectAnswers);
        startActivity(intent);
    }
}