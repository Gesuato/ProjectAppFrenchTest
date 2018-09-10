package model;

import java.io.Serializable;

/**
 * Created by victorgesuato on 2018-04-08.
 */

public class GoodAnswers implements Serializable{

    private int idQuestion;
    private String goodAnswer;

    public GoodAnswers() {
    }

    public GoodAnswers(int idQuestion, String goodAnswer) {
        this.idQuestion = idQuestion;
        this.goodAnswer = goodAnswer;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getGoodAnswer() {
        return goodAnswer;
    }

    public void setGoodAnswer(String goodAnswer) {
        this.goodAnswer = goodAnswer;
    }

    @Override
    public String toString() {
        return "" + idQuestion + "|" +  goodAnswer;
    }
}
