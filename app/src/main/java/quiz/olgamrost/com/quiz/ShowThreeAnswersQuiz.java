package quiz.olgamrost.com.quiz;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import quiz.olgamrost.com.quiz.Json.AnswersBean;
import quiz.olgamrost.com.quiz.Json.Response;

import static quiz.olgamrost.com.quiz.R.drawable.correct;
import static quiz.olgamrost.com.quiz.R.drawable.wrong;

public class ShowThreeAnswersQuiz extends Activity {

    TextView question;
    Button answer1, answer2, answer3;
    int numberOfQuestionsAnswered, totalQuestions, correctAnswers;
    Boolean solution1, solution2, solution3;
    String questionString, filePath;
    MediaPlayer wrongSound = null, correctSound = null;
    ArrayList<AnswerSolution> newlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_three_answers_quiz);

        question = (TextView) findViewById(R.id.question);
        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);

        Intent intent = getIntent();
        numberOfQuestionsAnswered = intent.getIntExtra("number", 0);
        correctAnswers = intent.getIntExtra("correctAnswers", 0);
        filePath = intent.getStringExtra("file");

        ResponseRepository repo = new ResponseRepository(getAssets());
        List<Response> list = repo.GetResponses(filePath);
        totalQuestions = list.size();

        Response currentResponse = list.get(numberOfQuestionsAnswered);
        questionString = currentResponse.getQuestion();
        List<AnswersBean> answers = currentResponse.getAnswers();

        question.setText(String.valueOf(numberOfQuestionsAnswered + 1) + ". " + questionString);

        wrongSound = new MediaPlayer().create(this, R.raw.wrong);
        correctSound = new MediaPlayer().create(this, R.raw.correct);

        randomizeAnswers(answers);

        disableOtherButtons();

        numberOfQuestionsAnswered++;
    }

    private void showCorrectAnswer() {

        if (solution1 == true) {
            answer1.setBackgroundResource(correct);
        } else if (solution2 == true) {
            answer2.setBackgroundResource(correct);
        } else if (solution3 == true) {
            answer3.setBackgroundResource(correct);
        }
    }

    private void randomizeAnswers(List<AnswersBean> answers) {

        AnswerSolution first = new AnswerSolution();
        String answer1String = answers.get(0).getAnswer();
        solution1 = answers.get(0).isSolution();
        first.setAnswer(answer1String);
        first.setSolution(solution1);

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

        newlist = new ArrayList<>(4);
        newlist.add(first);
        newlist.add(second);
        newlist.add(third);

        int i = 0;
        Random ran1 = new Random();

        while (i < newlist.size()) {
            int i1 = ran1.nextInt(newlist.size());
            System.out.println("first random: " + i1);
            answer1.setText(newlist.get(i1).getAnswer());
            solution1 = newlist.get(i1).isSolution();
            newlist.remove(newlist.get(i1));

            int i2 = ran1.nextInt(newlist.size());
            System.out.println("second random: " + i2);
            answer2.setText(newlist.get(i2).getAnswer());
            solution2 = newlist.get(i2).isSolution();
            newlist.remove(newlist.get(i2));

            int i3 = ran1.nextInt(newlist.size());
            System.out.println("third random: " + i3);
            answer3.setText(newlist.get(i3).getAnswer());
            solution3 = newlist.get(i3).isSolution();
            newlist.remove(newlist.get(i3));
        }
    }

    private void disableOtherButtons() {

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (solution1 == true) {
                    answer1.setBackgroundResource(correct);
                    correctAnswers++;
                    correctSound.start();
                } else {
                    answer1.setBackgroundResource(wrong);
                    wrongSound.start();
                }
                answer2.setEnabled(false);
                answer3.setEnabled(false);

                showCorrectAnswer();
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (solution2 == true) {
                    answer2.setBackgroundResource(correct);
                    correctAnswers++;
                    correctSound.start();
                } else {
                    answer2.setBackgroundResource(wrong);
                    wrongSound.start();
                }
                answer1.setEnabled(false);
                answer3.setEnabled(false);

                showCorrectAnswer();

            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (solution3 == true) {
                    answer3.setBackgroundResource(correct);
                    correctAnswers++;
                    correctSound.start();
                } else {
                    answer3.setBackgroundResource(wrong);
                    wrongSound.start();
                }
                answer1.setEnabled(false);
                answer2.setEnabled(false);

                showCorrectAnswer();
            }
        });


    }

    protected void showNextQuestion(View v) {

        if (numberOfQuestionsAnswered < totalQuestions) {
            final Intent intent = new Intent(this, ShowThreeAnswersQuiz.class);
            intent.putExtra("number", numberOfQuestionsAnswered);
            intent.putExtra("correctAnswers", correctAnswers);
            intent.putExtra("file", filePath);
            startActivity(intent);
        } else {
            final Intent intent = new Intent(this, FinalActivity.class);
            intent.putExtra("correctAnswers", correctAnswers);
            intent.putExtra("totalQuestions", totalQuestions);
            startActivity(intent);
        }
    }
}