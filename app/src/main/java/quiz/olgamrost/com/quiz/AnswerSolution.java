package quiz.olgamrost.com.quiz;

/**
 * Created by konz on 22.12.2016.
 */

public class AnswerSolution {
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isSolution() {
        return solution;
    }

    public void setSolution(boolean solution) {
        this.solution = solution;
    }

    private String answer;
    private boolean solution;
}
