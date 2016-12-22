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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        numberOfQuestionsAnswered = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showRepoQuestions(final View cmd){

        final Intent intent = new Intent(this, ShowRepoQuestionsActivity.class);
        intent.putExtra("number", numberOfQuestionsAnswered);
        startActivity(intent);
    }

    public void showQuestion (final View cmd){

        final Intent intent = new Intent(this, ShowQuestionsActivity.class);
        startActivity(intent);
    }

    public void showManyQuestions (final View cmd){

        List<Response> list = deserealiseJsonWithSeveralQuestions();

        // how to put responces in intent???


        Response questionAndAnswers = list.get(numberOfQuestionsAnswered);

        numberOfQuestionsAnswered++;

        Intent intent = new Intent(this, ShowManyQuestionsActivity.class);

       // intent.putExtra("quiz", list);

        Bundle bundle = new Bundle();
        bundle.putSerializable("question", questionAndAnswers);
     //   bundle.putSerializable("quiz", list);
        intent.putExtras(bundle);
        intent.putExtra("numberOfQuestionsAnswered", numberOfQuestionsAnswered);

        startActivity(intent);
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