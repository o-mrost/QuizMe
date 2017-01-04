package quiz.olgamrost.com.quiz;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int numberOfQuestionsAnswered, numberOfCorrectAnswers;
    String fileAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberOfQuestionsAnswered = 0;
        numberOfCorrectAnswers = 0;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case R.id.menuHelp:
                Intent intent = new Intent(this, ShowHelp.class);
                startActivity(intent);
                break;

            case R.id.menuSettings:
                Intent intent2 = new Intent(this, SettingsActivity.class);
                startActivity(intent2);
                break;

            case R.id.menuQuit:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.first_quiz) {
            fileAddress = "firstQuiz.txt";
            showQuestions(getCurrentFocus());

        } else if (id == R.id.second_quiz) {
            fileAddress = "secondQuiz.txt";
            showQuestions(getCurrentFocus());

        } else if (id == R.id.third_quiz) {
            fileAddress = "thirdQuiz.txt";
            showQuestions(getCurrentFocus());

        } else if (id == R.id.fourth_quiz) {
            fileAddress = "fourthQuiz.txt";
            showQuestions(getCurrentFocus());
        } else if (id == R.id.fifth_quiz) {
            fileAddress = "fifthQuiz.txt";
            showThreeAnswerQuiz(getCurrentFocus());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showQuestions(final View cmd) {

        final Intent intent = new Intent(this, ShowQuizActivity.class);
        intent.putExtra("number", numberOfQuestionsAnswered);
        intent.putExtra("correctAnswers", numberOfCorrectAnswers);
        intent.putExtra("file", fileAddress);
        startActivity(intent);

    }

    public void showThreeAnswerQuiz(final View cmd) {

        final Intent intent = new Intent(this, ShowThreeAnswersQuiz.class);
        intent.putExtra("number", numberOfQuestionsAnswered);
        intent.putExtra("correctAnswers", numberOfCorrectAnswers);
        intent.putExtra("file", fileAddress);
        startActivity(intent);

    }
}