package quiz.olgamrost.com.quiz.Json;

import java.util.List;

/**
 * Created by olgamrost on 08/12/2016.
 */
public class Response {
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public String get_id() {
        return _id;
    }

    /**
     * Sets id.
     *
     * @param _id the id
     */
    public void set_id(String _id) {
        this._id = _id;
    }

    /**
     * Gets updated at.
     *
     * @return the updated at
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets updated at.
     *
     * @param updatedAt the updated at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Gets created at.
     *
     * @return the created at
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets created at.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets question.
     *
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets question.
     *
     * @param question the question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Gets __ v.
     *
     * @return the __ v
     */
    public int get__v() {
        return __v;
    }

    /**
     * Sets __ v.
     *
     * @param __v the __ v
     */
    public void set__v(int __v) {
        this.__v = __v;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets answers.
     *
     * @return the answers
     */
    public List<AnswersBean> getAnswers() {
        return answers;
    }

    /**
     * Sets answers.
     *
     * @param answers the answers
     */
    public void setAnswers(List<AnswersBean> answers) {
        this.answers = answers;
    }
}
