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

        TextView answerTrueOrFalse = (TextView) findViewById(R.id.answerTrueOrFalse);
        answerTrueOrFalse.setText("The answer is correct!");
    }
    protected void  showQuestion (final View cmd){
        Intent intent = new Intent(this, ShowQuestionsPage.class);
        startActivity(intent);
    }
}
