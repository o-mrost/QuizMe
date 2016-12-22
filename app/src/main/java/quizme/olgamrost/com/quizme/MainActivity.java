package quizme.olgamrost.com.quizme;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int numberOfQuestionsAnswered;

    public static final String KEY_NUMBEROFQUESTIONS = "questions";
    
    public static final boolean DBG = true;
    private static final String CNAME = "MainActivity.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        numberOfQuestionsAnswered = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showQuestion (final View cmd){

        final Intent intent = new Intent(this, ShowQuestionsActivity.class);
        //intent.putExtra(KEY_NUMBEROFQUESTIONS, numberOfQuestionsAnswered);
        startActivity(intent);
    }

    public void showManyQuestions (final View cmd){

        List<Responce> responses = deserealiseJsonWithSeveralQuestions();

        Responce questionAndAnswers = responses.get(numberOfQuestionsAnswered);
        String question = questionAndAnswers.getQuestion();
        //Log.v("question... ", question);
       // Log.v("number of qu answered: ", "" + numberOfQuestionsAnswered);
        List<Responce.AnswersBean> answers = questionAndAnswers.getAnswers();
        String answer1 = answers.get(0).getAnswer();
        //Log.v("answer...", answer1);
        boolean solution1 = answers.get(0).isSolution();

        String answer2 = answers.get(1).getAnswer();
        boolean solution2 = answers.get(1).isSolution();

        String answer3 = answers.get(2).getAnswer();
        boolean solution3 = answers.get(2).isSolution();

        String answer4 = answers.get(3).getAnswer();
        boolean solution4 = answers.get(3).isSolution();

        String[] answersArray = new String[]{answer1, answer2, answer3, answer4};
        Boolean [] solutionsArray = new Boolean[]{solution1, solution2, solution3, solution4};

        numberOfQuestionsAnswered++;

        final Intent intent = new Intent(this, ShowManyQuestionsActivity.class);

        intent.putExtra("answersArray", answersArray);
        intent.putExtra("solutionsArray", solutionsArray);


        intent.putExtra("question", question);
        intent.putExtra("answer1", answer1);
        intent.putExtra("solution1", solution1);
        intent.putExtra("answer2", answer2);
        intent.putExtra("solution2", solution2);
        intent.putExtra("answer3", answer3);
        intent.putExtra("solution3", solution3);
        intent.putExtra("answer4", answer4);
        intent.putExtra("solution4", solution4);

        startActivity(intent);
    }

    private List<Responce> deserealiseJsonWithSeveralQuestions() {

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

            //Log.v("-- responcestr -- ", responcestr);
            Gson gson = new Gson();

            // http://stackoverflow.com/questions/9598707/gson-throwing-expected-begin-object-but-was-begin-array
            Type collectionType = new TypeToken<Collection<Responce>>(){}.getType();
            Collection<Responce> items = gson.fromJson(responcestr, collectionType);

//            for (Responce item : items) {
//                Log.v("--- item: ---", item.toString());
//            }

            List<Responce> list = (List<Responce>) items;

            for (Responce item : list) {
                Log.v("---list item: ---", item.toString());
                Log.v("- list item question -", item.getQuestion());
                item.getAnswers();
                List<Responce.AnswersBean> answers = item.getAnswers();
                for (Responce.AnswersBean answer : answers) {
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