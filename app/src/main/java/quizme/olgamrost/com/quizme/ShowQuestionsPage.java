package quizme.olgamrost.com.quizme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by olgamrost on 04/12/2016.
 */
public class ShowQuestionsPage extends Activity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.showquestion);
    }

    protected void checkAnswer(final View cmd){

        final Intent intent = new Intent(this, ShowCorrectAnswerActivity.class);
        startActivity(intent);
    }
}
