package quiz.olgamrost.com.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int numberOfQuestionsAnswered, numberOfCorrectAnswers;
    String fileAddress, fromServer = "no";

    /**
     * Method for creation the layout of the main activity.
     * Initialises also the two variables that will be later used to count
     * correct answers
     */
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

    /**
     * Method to indicate what happens when the user clicks either help
     * or quit item in the menu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case R.id.menuHelp:
                Intent intent = new Intent(this, ShowHelp.class);
                startActivity(intent);
                break;

            case R.id.menuQuit:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Method to indicate what happens when the user clicks on one of the quizez
     * from the navigation drawer
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.first_quiz) {
            fileAddress = "firstQuiz.txt";
            showQuestions(getCurrentFocus());

        } else if (id == R.id.second_quiz) {
            fromServer = "yes";
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

    /**
     * Opens new activity with extras, which shows a quiz with four answers
     */
    public void showQuestions(final View cmd) {

        final Intent intent = new Intent(this, ShowQuizActivity.class);
        intent.putExtra("number", numberOfQuestionsAnswered);
        intent.putExtra("correctAnswers", numberOfCorrectAnswers);
        intent.putExtra("file", fileAddress);
        intent.putExtra("server", fromServer);
        startActivity(intent);

    }

    /**
     * Opens new activity with extras, which shows a quiz with three answers and
     * different layout
     */
    public void showThreeAnswerQuiz(final View cmd) {

        final Intent intent = new Intent(this, ShowThreeAnswersQuiz.class);
        intent.putExtra("number", numberOfQuestionsAnswered);
        intent.putExtra("correctAnswers", numberOfCorrectAnswers);
        intent.putExtra("file", fileAddress);
        startActivity(intent);

    }
}