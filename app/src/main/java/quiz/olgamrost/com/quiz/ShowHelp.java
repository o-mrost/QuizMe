package quiz.olgamrost.com.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ShowHelp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_help);
    }

    protected void goBack(View v){

        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
