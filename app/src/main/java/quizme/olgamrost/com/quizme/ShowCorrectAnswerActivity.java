package quizme.olgamrost.com.quizme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by olgamrost on 04/12/2016.
 */

public class ShowCorrectAnswerActivity extends Activity {

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.showanswer);

        final Bundle extras = getIntent().getExtras();

        if (extras != null){
            TextView questionOnShowAnswer = (TextView) findViewById(R.id.questionOnShowAnswer);
            TextView answerTrueOrFalse = (TextView) findViewById(R.id.answerTrueOrFalse);

            questionOnShowAnswer.setText(extras.getString(ShowQuestionsActivity.KEY_QUESTION));
            answerTrueOrFalse.setText("You clicked button: " + extras.getInt(ShowQuestionsActivity.KEY_BUTTON));
        }
    }

    protected void  showQuestion (final View cmd){
        Intent intent = new Intent(this, ShowQuestionsActivity.class);

        startActivity(intent);
    }
}