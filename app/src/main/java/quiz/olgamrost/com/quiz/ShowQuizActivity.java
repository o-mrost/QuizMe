package quiz.olgamrost.com.quiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import quiz.olgamrost.com.quiz.Json.AnswersBean;
import quiz.olgamrost.com.quiz.Json.Response;
import static quiz.olgamrost.com.quiz.R.drawable.correct;
import static quiz.olgamrost.com.quiz.R.drawable.wrong;


public class ShowQuizActivity extends AppCompatActivity {

    TextView question;
    Button answer1, answer2, answer3, answer4, nextQuestion;
    int numberOfQuestionsAnswered, totalQuestions, correctAnswers;
    Boolean solution1, solution2, solution3, solution4;
    String questionString, filePath, fromServer = "no", jsonFileText;
    MediaPlayer mp1 = null, mp2 = null;
    List<Response> list;


    /**
     * Creates the layout of the activity and contains the main logic of the app
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_quiz);

        question = (TextView) findViewById(R.id.question);
        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);
        nextQuestion = (Button) findViewById(R.id.nextQuestion);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Intent intent = getIntent();
        numberOfQuestionsAnswered = intent.getIntExtra("number", 0);
        correctAnswers = intent.getIntExtra("correctAnswers", 0);
        fromServer = intent.getStringExtra("server");

        // if the quiz is from server

        if (fromServer.equals("yes")) {

            getServerResponse();

            Log.v("*** jsonFileText url ", "  " + jsonFileText);
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<Response>>() {
            }.getType();
            Collection<Response> items = gson.fromJson(jsonFileText, collectionType);
            list = (List<Response>) items;

        } else {

            filePath = intent.getStringExtra("file");
            if (filePath == null) {
                filePath = "firstQuiz.txt";
            }

            ResponseRepository repo = new ResponseRepository(getAssets());
            list = repo.GetResponses(filePath);
        }

        totalQuestions = list.size();

        if (totalQuestions - numberOfQuestionsAnswered == 1) {
            nextQuestion.setText("Show game summary");
        }

        Response currentResponse = list.get(numberOfQuestionsAnswered);
        questionString = currentResponse.getQuestion();
        List<AnswersBean> answers = currentResponse.getAnswers();

        question.setText(String.valueOf(numberOfQuestionsAnswered + 1) + ". " + questionString);
        mp1 = new MediaPlayer().create(this, R.raw.wrong);
        mp2 = new MediaPlayer().create(this, R.raw.correct);

        randomizeAnswers(answers);

        disableOtherButtons();

        numberOfQuestionsAnswered++;
    }

    /**
     * Randomizes answers and sets the four different answers on the buttons
     */
    private void randomizeAnswers(List<AnswersBean> answers) {

        Log.v("*** randomizeAnswers", "opened");

        AnswerSolution first = new AnswerSolution();
        String answer1String = answers.get(0).getAnswer();
        solution1 = answers.get(0).isSolution();
        first.setAnswer(answer1String);
        first.setSolution(solution1);

        // Declaration of other three items is collapsed for brevity
        // ...
        // region
        AnswerSolution second = new AnswerSolution();
        String answer2String = answers.get(1).getAnswer();
        solution2 = answers.get(1).isSolution();
        second.setAnswer(answer2String);
        second.setSolution(solution2);

        AnswerSolution third = new AnswerSolution();
        String answer3String = answers.get(2).getAnswer();
        solution3 = answers.get(2).isSolution();
        third.setAnswer(answer3String);
        third.setSolution(solution3);

        AnswerSolution fourth = new AnswerSolution();
        String answer4String = answers.get(3).getAnswer();
        solution4 = answers.get(3).isSolution();
        fourth.setAnswer(answer4String);
        fourth.setSolution(solution4);
        // endregion
        ArrayList<AnswerSolution> newlist = new ArrayList<>(4);
        newlist.add(first);
        newlist.add(second);
        newlist.add(third);
        newlist.add(fourth);

        int i = 0;
        Random ran1 = new Random();

        while (i < newlist.size()) {
            int i1 = ran1.nextInt(newlist.size());
            answer1.setText(newlist.get(i1).getAnswer());
            solution1 = newlist.get(i1).isSolution();
            newlist.remove(newlist.get(i1));

            // Randomizing of other three items is collapsed for brevity
            // ...
            //region
            int i2 = ran1.nextInt(newlist.size());
            answer2.setText(newlist.get(i2).getAnswer());
            solution2 = newlist.get(i2).isSolution();
            newlist.remove(newlist.get(i2));

            int i3 = ran1.nextInt(newlist.size());
            answer3.setText(newlist.get(i3).getAnswer());
            solution3 = newlist.get(i3).isSolution();
            newlist.remove(newlist.get(i3));

            int i4 = ran1.nextInt(newlist.size());
            answer4.setText(newlist.get(i4).getAnswer());
            solution4 = newlist.get(i4).isSolution();
            newlist.remove(newlist.get(i4));

            //endregion
        }
    }

    /**
     * After clicking on one of the answers, the other buttons are disabled
     */
    private void disableOtherButtons() {

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (solution1 == true) {
                    answer1.setBackgroundResource(correct);
                    correctAnswers++;
                    mp2.start();
                } else {
                    answer1.setBackgroundResource(wrong);
                    mp1.start();
                }

                answer2.setEnabled(false);
                answer3.setEnabled(false);
                answer4.setEnabled(false);
                showCorrectAnswer();
            }

        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (solution2 == true) {
                    answer2.setBackgroundResource(correct);
                    correctAnswers++;
                    mp2.start();
                } else {
                    answer2.setBackgroundResource(wrong);
                    mp1.start();
                }

                answer1.setEnabled(false);
                answer3.setEnabled(false);
                answer4.setEnabled(false);
                showCorrectAnswer();
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (solution3 == true) {
                    answer3.setBackgroundResource(correct);
                    correctAnswers++;
                    mp2.start();
                } else {
                    answer3.setBackgroundResource(wrong);
                    mp1.start();
                }
                answer1.setEnabled(false);
                answer2.setEnabled(false);
                answer4.setEnabled(false);
                showCorrectAnswer();
            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (solution4 == true) {
                    answer4.setBackgroundResource(correct);
                    correctAnswers++;
                    mp2.start();
                } else {
                    answer4.setBackgroundResource(wrong);
                    mp1.start();
                }
                answer1.setEnabled(false);
                answer2.setEnabled(false);
                answer3.setEnabled(false);
                showCorrectAnswer();
            }
        });
    }

    private void showCorrectAnswer() {

        if (solution1 == true) {
            answer1.setBackgroundResource(correct);
        } else if (solution2 == true) {
            answer2.setBackgroundResource(correct);
        } else if (solution3 == true) {
            answer3.setBackgroundResource(correct);
        } else if (solution4 == true) {
            answer4.setBackgroundResource(correct);
        }
    }

    /**
     * Show next question.
     *
     * @param v the v
     */
    protected void showNextQuestion(View v) {

        if (numberOfQuestionsAnswered < totalQuestions) {
            final Intent intent = new Intent(this, ShowQuizActivity.class);
            intent.putExtra("number", numberOfQuestionsAnswered);
            intent.putExtra("correctAnswers", correctAnswers);
            intent.putExtra("file", filePath);
            intent.putExtra("server", fromServer);
            startActivity(intent);
        } else {
            final Intent intent = new Intent(this, FinalActivity.class);
            intent.putExtra("correctAnswers", correctAnswers);
            intent.putExtra("totalQuestions", totalQuestions);
            startActivity(intent);
        }
    }

    /**
     * Gets server response.
     */
    public void getServerResponse() {
        URL url = null;
        try {
          url = new URL("http://quizmerbn.azurewebsites.net/networks");
//            url = new URL("http://10.0.2.2:3000/networks");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            InputStream in = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            System.out.println("+++ result +++ " + result.toString());

            jsonFileText = result.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
    }
}