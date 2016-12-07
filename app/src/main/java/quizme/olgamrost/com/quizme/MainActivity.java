package quizme.olgamrost.com.quizme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);}

    public void onClickMenu (final View cmd)
    {

        startActivity(new Intent(this, MenuMain.class));

//hallo
    }
}
