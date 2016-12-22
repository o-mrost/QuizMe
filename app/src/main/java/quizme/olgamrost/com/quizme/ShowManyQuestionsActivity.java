package quizme.olgamrost.com.quizme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    int numberOfQuestionsAnswered;

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

        Intent i = getIntent();
        numberOfQuestionsAnswered = i.getIntExtra("numberOfQuestionsAnswered", 0);
        Response resp = (Response) i.getSerializableExtra("question");

        String questionString = resp.getQuestion();
        List<AnswersBean> answers = resp.getAnswers();

        String answer1String = answers.get(0).getAnswer();
        boolean solution1 = answers.get(0).isSolution();

        String answer2String = answers.get(1).getAnswer();
        boolean solution2 = answers.get(1).isSolution();

        String answer3String = answers.get(2).getAnswer();
        boolean solution3 = answers.get(2).isSolution();

        String answer4String = answers.get(3).getAnswer();
        boolean solution4 = answers.get(3).isSolution();

        question.setText(questionString);
        answer1.setText(answer1String);
        answer2.setText(answer2String);
        answer3.setText(answer3String);
        answer4.setText(answer4String);
        questionNumber.setText(String.valueOf(numberOfQuestionsAnswered));

        ResponseRepository repo = new ResponseRepository(getAssets());

        List<Response> list = repo.GetResponses();

        for (int j = 0; j< list.size(); j++)
        {
            Response currentResp = list.get(j);
            if (currentResp.get_id().equals(resp.get_id()))
            {
                //current question index of list is in j
                Response nextResponse = list.get(j+1);

            }
        }
    }

    protected void showNextQuestion(final View cmd){


    }

    private List<Response> deserealiseJsonWithSeveralQuestions() {

        AssetManager am = getAssets();
        try {
            InputStream is = am.open("twoJsons.txt");

            BufferedReader bR = new BufferedReader(new InputStreamReader(is));
            String line = "";

            StringBuilder responseStrBuilder = new StringBuilder();
            while ((line = bR.readLine()) != null) {
                responseStrBuilder.append(line);
            }
            is.close();

            String responcestr = responseStrBuilder.toString();

            Gson gson = new Gson();

            // http://stackoverflow.com/questions/9598707/gson-throwing-expected-begin-object-but-was-begin-array
            Type collectionType = new TypeToken<Collection<Response>>(){}.getType();
            Collection<Response> items = gson.fromJson(responcestr, collectionType);

//            for (Responce item : items) {
//                Log.v("--- item: ---", item.toString());
//            }

            List<Response> list = (List<Response>) items;

            for (Response item : list) {
                Log.v("---list item: ---", item.toString());
                Log.v("- list item question -", item.getQuestion());
                item.getAnswers();
                List<AnswersBean> answers = item.getAnswers();
                for (AnswersBean answer : answers) {
                    Log.v("-- list item answer --", answer.getAnswer());
                }
            }

            return list;

        } catch (IOException e) {
            e.printStackTrace();
            //} catch (JSONException e) {
            //  e.printStackTrace();
        }
        return null;
    }

}