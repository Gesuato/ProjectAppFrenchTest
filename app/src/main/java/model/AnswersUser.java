package model;

import android.text.SpannableStringBuilder;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by victorgesuato on 2018-04-06.
 */

public class AnswersUser implements Serializable {

    private int idQuestion;
    private String question;
    private int[] answers;
    private String result;
    private String reponse;

    public AnswersUser() {

        this.result = "Mauvaise réponse";
        this.answers = new int[4];
        this.reponse = Integer.toString(answers[0]) + Integer.toString(answers[1]) + Integer.toString(answers[2]) + Integer.toString(answers[3]);

    }


    public AnswersUser(int idQuestion, String question, int[] answers, String result, String reponse) {
        this.idQuestion = idQuestion;
        this.question = question;
        this.answers = answers;
        this.result = result;
        this.reponse = reponse;
    }

    public void setAnswers(int item, int index) {
        this.answers[index] = item;
    }

    public int getAnswers(int index) {

        return answers[index];
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int[] getAnswers() {
        return answers;
    }

    public void setAnswers(int[] answers) {
        this.answers = answers;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public void setReponse() {
        this.reponse = Integer.toString(answers[0]) + Integer.toString(answers[1]) + Integer.toString(answers[2]) + Integer.toString(answers[3]);
    }

    public boolean isDoItLater() {

        boolean status = false;

        for(int i = 0;i < answers.length;i++){
            if(answers[i] != 0){
                status = true;
                break;
            }
        }
        return status;
    }

    public void clearAnswers() {

        for(int i = 0;i < 4;i++){
            answers[i] = 0;
        }

    }

    @Override
    public String toString() {
        return "Question " + (idQuestion + 1) + " | " + result;
    }
}
