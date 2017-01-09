package quiz.olgamrost.com.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

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

            Log.v(" open second quiz", "dd");
            // get json
            RequestQueue queue = Volley.newRequestQueue(this);

            final String response1 = "error";

            // if it works, change this string to our url
            final String url = "http://httpbin.org/get?param1=hello";

            // prepare the Request
            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // display response
                            Log.d("Response", response.toString());

                            try {
                                Log.v("storage writable", String.valueOf(isExternalStorageWritable()));
                                Log.v("storage readable", String.valueOf(isExternalStorageReadable()));

                                String address = "secondQuiz.txt";
                                final File dir = new File(getApplicationContext().getFilesDir() + "/Quizez");
                                dir.mkdirs();

                                final File file = new File(dir, address);
                                Log.v("file address ", file.toString());

                                FileOutputStream outputStream = new FileOutputStream(file);
                                outputStream.write((response.toString()).getBytes());
                                outputStream.close();

                                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                                    String line;
                                    while ((line = br.readLine()) != null) {
                                        // process the line.
                                        Log.v("contents of file: ", line);
                                    }
                                }

                                fileAddress = file.toString();
                                Log.v("add:  ", fileAddress);

                                Toast.makeText(getApplicationContext(), "File saved", Toast.LENGTH_LONG).show();

                            } catch (Exception e) {
                                Log.v("Exception: ", e.getMessage());
                                Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                            finish();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Error.Response", response1);
                        }
                    }
            );

            // add it to the RequestQueue
            queue.add(getRequest);

//            fileAddress = "secondQuiz.txt";
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


    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}