package quiz.olgamrost.com.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by olgamrost on 22/12/2016.
 */

public class FinalActivity extends Activity {

    double correctAnswersInt, totalQuestions;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_final);

        Intent intent = getIntent();
        correctAnswersInt = intent.getIntExtra("correctAnswers", 0);
        totalQuestions = intent.getIntExtra("totalQuestions", 0);

        TextView correctAnswers = (TextView) findViewById(R.id.correctAnswers);
        TextView totalResult = (TextView) findViewById(R.id.totalResult);

        double score = correctAnswersInt / totalQuestions * 100;

        correctAnswers.setText("You answered correctly " + (int) correctAnswersInt + " questions");
        totalResult.setText("Your score is " + score + "%");
    }
}