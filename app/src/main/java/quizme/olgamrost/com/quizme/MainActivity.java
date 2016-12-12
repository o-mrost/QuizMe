package quizme.olgamrost.com.quizme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    int numberOfQuestionsAnswered;

    public static final String KEY_NUMBEROFQUESTIONS = "questions";

    // variable for debuggingjhjhjhj
    public static final boolean DBG = true;
    private static final String CNAME = "MainActivity.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        numberOfQuestionsAnswered = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showQuestion (final View cmd){

        final Intent intent = new Intent(this, ShowQuestionsActivity.class);
        intent.putExtra(KEY_NUMBEROFQUESTIONS, numberOfQuestionsAnswered);
        startActivity(intent);
    }
}