package quizme.olgamrost.com.quizme;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by olgamrost on 04/12/2016.
 */
public class ShowQuestionsActivity extends Activity {

    String questionString, answerString, answerString1, answerString2, answerString3;
    String[] answersArrayString;
    Boolean[] solutionArrayBool;
    Boolean answerBool, answerBool1, answerBool2, answerBool3;
    TextView question, questionNumber;
    Button answer1, answer2, answer3, answer4;
    int questionsAnswered;

    public static final String KEY_ANSWERS = "answers";
    public static final String KEY_SOLUTIONS = "solutions";
    public static final String KEY_QUESTION = "question";
    public static final String KEY_BUTTON = "buttonPressed";
    public static final String KEY_NUMBEROFQUESTIONS = "questions";

    @Override
    protected void onCreate(Bundle bundle) {

        super.onCreate(bundle);
        setContentView(R.layout.showquestion);

        final Bundle extras = getIntent().getExtras();
        questionsAnswered = extras.getInt(MainActivity.KEY_NUMBEROFQUESTIONS);
        questionsAnswered++;

        Log.v("questions answered ", "" + questionsAnswered);

        question = (TextView) findViewById(R.id.question);
        questionNumber = (TextView) findViewById(R.id.questionNumber);

        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);

        deserialiseJson();

        question.setText(questionString);
        questionNumber.setText(questionsAnswered + ".");
        answer1.setText(answerString);
        answer2.setText(answerString1);
        answer3.setText(answerString2);
        answer4.setText(answerString3);

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerBool == true) {
                    answer1.setBackgroundColor(Color.GREEN);
                } else {
                    answer1.setBackgroundColor(Color.RED);
                }
                ;
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerBool1 == true) {
                    answer2.setBackgroundColor(Color.GREEN);
                } else {
                    answer2.setBackgroundColor(Color.RED);
                }
                ;
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerBool2 == true) {
                    answer3.setBackgroundColor(Color.GREEN);
                } else {
                    answer3.setBackgroundColor(Color.RED);
                }
                ;
            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerBool3 == true) {
                    answer4.setBackgroundColor(Color.GREEN);
                } else {
                    answer4.setBackgroundColor(Color.RED);
                }
                ;
            }
        });

    }

    private void deserialiseJson() {
        questionString = "sample question";

        // try to get json object from the json.txt file
        // this file is in the assest folder (chose Project, not Android)
        AssetManager am = getAssets();

        try {
            InputStream is = am.open("Json.txt");

            BufferedReader bR = new BufferedReader(new InputStreamReader(is));
            String line = "";

            StringBuilder responseStrBuilder = new StringBuilder();
            while ((line = bR.readLine()) != null) {

                responseStrBuilder.append(line);
            }
            is.close();

            // wrote contents of json (without first square brackets to a JSONObject)
            JSONObject json = new JSONObject(responseStrBuilder.toString());

            questionString = json.getString("question");
            Log.v("___ question ___", questionString);

            JSONArray answersArray = json.getJSONArray("answers");

            JSONObject answerObject = answersArray.getJSONObject(0);
            Log.v("+++++", answerObject.toString());

            answerString = answerObject.getString("answer");
            answerBool = answerObject.getBoolean("solution");
            Log.v("--- answerString ---", answerString);
            Log.v("--- true - false ---", answerBool.toString());

            JSONObject answerObject1 = answersArray.getJSONObject(1);
            answerString1 = answerObject1.getString("answer");
            answerBool1 = answerObject1.getBoolean("solution");
            Log.v("--- answerString1 ---", answerString1);
            Log.v("---   +++++", answerObject1.toString());

            JSONObject answerObject2 = answersArray.getJSONObject(2);
            answerString2 = answerObject2.getString("answer");
            answerBool2 = answerObject2.getBoolean("solution");
            Log.v("--- answerString2 ---", answerString2);

            JSONObject answerObject3 = answersArray.getJSONObject(3);
            answerString3 = answerObject3.getString("answer");
            answerBool3 = answerObject3.getBoolean("solution");
            Log.v("--- answerString3 ---", answerString3);


//            answersArrayString = new String[answerArray.length()];
//            solutionArrayBool = new Boolean[answerArray.length()];
//
//            for (int i = 0; i < answerArray.length(); i++) {
//
//                JSONObject answerObject = answerArray.getJSONObject(i);
//
//                String answer = answerObject.getString("answer");
//                Log.v("__ answer" + i + " __", answer);
//                answersArrayString[i] = answer;
//
//                Boolean solution = answerObject.getBoolean("solution");
//                Log.v("__ solution __ ", solution.toString());
//                solutionArrayBool[i] = solution;
//            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    protected void checkAnswer() {


    }

    protected void showNextQuestion(final View cmd) {

        final Intent intent = new Intent(this, ShowCorrectAnswerActivity.class);

        // this button was clicked, but how to get true-false answer?
        intent.putExtra(KEY_BUTTON, cmd.getId());

        Log.v("button ", "clicked: " + cmd.getId());

        // information to pass to another activity
        intent.putExtra(KEY_QUESTION, questionString);
        //intent.putExtra(KEY_ANSWERS, answersArrayString);
        // intent.putExtra(KEY_SOLUTIONS, solutionArrayBool);
        intent.putExtra(KEY_NUMBEROFQUESTIONS, questionsAnswered);

        startActivity(intent);
    }
}