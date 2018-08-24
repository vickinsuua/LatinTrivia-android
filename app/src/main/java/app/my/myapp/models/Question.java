package app.my.myapp.models;

import android.content.Intent;

import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("_id")
    private String _id;
    @SerializedName("question")
    private String question;
    @SerializedName("correctAnswer")
    private String correctAnswer;
    @SerializedName("optionB")
    private String optionB;
    @SerializedName("optionC")
    private String optionC;
    @SerializedName("category")
    private String category;

    public Question(String question, String correctAnswer, String optionB, String optionC, String category) {
        super();
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.optionB = optionB;
        this.optionC = optionC;
        this.category = category;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Question(String _id, String question, String correctAnswer, String optionB, String optionC, String category) {
        this._id = _id;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.optionB = optionB;
        this.optionC = optionC;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Question{" +
                "_id='" + _id + '\'' +
                ", question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
