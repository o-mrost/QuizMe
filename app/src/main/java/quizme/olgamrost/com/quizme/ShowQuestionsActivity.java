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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static android.R.attr.button;

/**
 * Created by olgamrost on 04/12/2016.
 */
public class ShowQuestionsActivity extends Activity {

    String questionString, answerString, answerString1, answerString2, answerString3;

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

        deserialiseJson();

        int i = 0;

        String[] answersArray={answerString,answerString1,answerString2,answerString3};
        System.out.println("answers array: " + answersArray.toString());

        for (int e=0; e<answersArray.length; e++) {
            Log.v("++++ ---++", answersArray[e]);
            }

        ArrayList<String> list = new ArrayList<String>(Arrays.asList(answersArray));

        AnswerSolution first = new AnswerSolution();
        first.setAnswer(answerString);
        first.setSolution(answerBool);

        AnswerSolution second = new AnswerSolution();
        second.setAnswer(answerString1);
        second.setSolution(answerBool1);

        AnswerSolution third = new AnswerSolution();
        third.setAnswer(answerString2);
        third.setSolution(answerBool2);

        AnswerSolution fourth = new AnswerSolution();
        fourth.setAnswer(answerString3);
        fourth.setSolution(answerBool3);

        ArrayList<AnswerSolution> newlist = new ArrayList<>(4);
        newlist.add(first);
        newlist.add(second);
        newlist.add(third);
        newlist.add(fourth);

        Random ran1 = new Random();
        Random ran2 = new Random();
        Random ran3 = new Random();
        Random ran4 = new Random();

        super.onCreate(bundle);
        setContentView(R.layout.showquestion);

        question = (TextView) findViewById(R.id.question);
        questionNumber = (TextView) findViewById(R.id.questionNumber);

        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);

        question.setText(questionString);
        questionNumber.setText(questionsAnswered + ".");


//        while (i < list.size()) {
//            int i1 = ran1.nextInt(list.size());
//            answer1.setText(list.get(i1));
//            list.remove(list.get(i1));
//
//            int i2 = ran2.nextInt(list.size());
//            answer2.setText(list.get(i2));
//            list.remove(list.get(i2));
//
//            int i3 = ran3.nextInt(list.size());
//            answer3.setText(list.get(i3));
//            list.remove(list.get(i3));
//
//            int i4 = ran4.nextInt(list.size());
//            answer4.setText(list.get(i4));
//            list.remove(list.get(i4));
//
//        }

        while (i < newlist.size()) {
            int i1 = ran1.nextInt(newlist.size());
            answer1.setText(newlist.get(i1).getAnswer());
            answerBool=newlist.get(i1).isSolution();
            newlist.remove(newlist.get(i1));

            int i2 = ran2.nextInt(newlist.size());
            answer2.setText(newlist.get(i2).getAnswer());
            answerBool1=newlist.get(i2).isSolution();
            newlist.remove(newlist.get(i2));

            int i3 = ran3.nextInt(newlist.size());
            answer3.setText(newlist.get(i3).getAnswer());
            answerBool2=newlist.get(i3).isSolution();
            newlist.remove(newlist.get(i3));

            int i4 = ran4.nextInt(newlist.size());
            answer4.setText(newlist.get(i4).getAnswer());
            answerBool3=newlist.get(i4).isSolution();
            newlist.remove(newlist.get(i4));
        }

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerBool == true) {
                    answer1.setBackgroundColor(Color.GREEN);
                } else {
                    answer1.setBackgroundColor(Color.RED);
                }

                answer2.setEnabled(false);
                answer3.setEnabled(false);
                answer4.setEnabled(false);
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

                answer1.setEnabled(false);
                answer3.setEnabled(false);
                answer4.setEnabled(false);
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
                answer1.setEnabled(false);
                answer2.setEnabled(false);
                answer4.setEnabled(false);
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
                answer1.setEnabled(false);
                answer2.setEnabled(false);
                answer3.setEnabled(false);
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


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
        //intent.putExtra(KEY_NUMBEROFQUESTIONS, questionsAnswered);

        startActivity(intent);
    }
}