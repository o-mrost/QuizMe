package quizme.olgamrost.com.quizme;

import java.util.List;

/**
 * Created by olgamrost on 08/12/2016.
 */

public class Responce {


    /**
     * _id : 58446e0c37b3113b6b024be4
     * updatedAt : 2016-12-04T19:56:21.420Z
     * createdAt : 2016-12-04T19:27:08.914Z
     * question : The TCP protocol is assigned to which OSI layer?
     * __v : 4
     * answers : [{"answer":"Transport Layer","solution":true,"_id":"5844744155b10b3d26489773"},{"answer":"Session Layer","solution":false,"_id":"5844749955b10b3d26489774"},{"answer":"Network Layer","solution":false,"_id":"5844749f55b10b3d26489775"},{"answer":"Presentation Layer","solution":false,"_id":"584474e555b10b3d26489776"}]
     * category : Information technology
     */

    private String _id;
    private String updatedAt;
    private String createdAt;
    private String question;
    private int __v;
    private String category;
    private List<AnswersBean> answers;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<AnswersBean> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswersBean> answers) {
        this.answers = answers;
    }

    public static class AnswersBean {
        /**
         * answer : Transport Layer
         * solution : true
         * _id : 5844744155b10b3d26489773
         */

        private String answer;
        private boolean solution;
        private String _id;

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

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }
    }
}
