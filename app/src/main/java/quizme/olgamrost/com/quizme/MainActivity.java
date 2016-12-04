package quizme.olgamrost.com.quizme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity extends AppCompatActivity {

    // variable for debugging
    public static final boolean DBG = true;
    private static final String CNAME = "MainActivity.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showQuestion (final View cmd){

        final Intent intent = new Intent(this, ShowQuestionsPage.class);
        startActivity(intent);
    }
}