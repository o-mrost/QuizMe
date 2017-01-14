package quiz.olgamrost.com.quiz;

/**
 * Created by konz on 22.12.2016.
 */
public class AnswerSolution {
    /**
     * Gets answer.
     *
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Sets answer.
     *
     * @param answer the answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Is solution boolean.
     *
     * @return the boolean
     */
    public boolean isSolution() {
        return solution;
    }

    /**
     * Sets solution.
     *
     * @param solution the solution
     */
    public void setSolution(boolean solution) {
        this.solution = solution;
    }

    private String answer;
    private boolean solution;
}
