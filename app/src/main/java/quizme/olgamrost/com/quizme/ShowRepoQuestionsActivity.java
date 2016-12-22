package quizme.olgamrost.com.quizme;

import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by olgamrost on 22/12/2016.
 */

public class ShowRepoQuestionsActivity extends Activity{

    TextView question, questionNumber;
    Button answer1, answer2, answer3, answer4;
    ResponseRepository repo;
    List<Response> list;
    Response currentResponse;
    int numberOfQuestionsAnswered, totalQuestions;

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
        numberOfQuestionsAnswered = i.getIntExtra("number", 0);

        repo = new ResponseRepository(getAssets());
        list = repo.GetResponses();
        totalQuestions = list.size();
        Log.v("total: ", String.valueOf(totalQuestions));

        currentResponse = list.get(numberOfQuestionsAnswered);

        String questionString = currentResponse.getQuestion();
        List<AnswersBean> answers = currentResponse.getAnswers();

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
        questionNumber.setText(String.valueOf(numberOfQuestionsAnswered + 1));

        numberOfQuestionsAnswered++;
    }

   protected void showNextQuestion (View v){

       //Response nextResponse = repo.GetNext(currentResponse);
       if (numberOfQuestionsAnswered < totalQuestions) {
           final Intent intent = new Intent(this, ShowRepoQuestionsActivity.class);
           intent.putExtra("number", numberOfQuestionsAnswered);
           startActivity(intent);
       } else {
           // show final screen
           final Intent intent = new Intent(this, FinalActivity.class);
           startActivity(intent);
       }

   }
}