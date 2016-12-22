package quizme.olgamrost.com.quizme;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by olgamrost on 08/12/2016.
 */

// try to show several questions one after the other
public class ShowManyQuestionsActivity extends Activity {

    TextView question, questionNumber;
    Button answer1, answer2, answer3, answer4;
    //Gson gson;
    //CustomAdapter adapter;
// Responce responceObject;
    //Responce2 responce2Ojb;
    //CustomAdapter2 customAdapter2;

    @Override
    protected void onCreate(Bundle savedInstance) {

        super.onCreate(savedInstance);
        setContentView(R.layout.showquestion);

        question = (TextView) findViewById(R.id.question);
        questionNumber = (TextView) findViewById(R.id.questionNumber);

        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);

        String [] answersStringArray = null;
        Boolean [] answersBooleanArray = null;

        final Bundle extras = getIntent().getExtras();

        if (extras != null){
            question.setText(extras.getString("question"));
            answer1.setText(extras.getString("answer1"));
            answer2.setText(extras.getString("answer2"));
            answer3.setText(extras.getString("answer3"));
            answer4.setText(extras.getString("answer4"));


        }
    }
}