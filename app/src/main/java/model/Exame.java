package model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by victorgesuato on 2018-04-05.
 */

public class Exame implements Serializable{

    private String examenTitle;
    private String examenId;
    private List<Question> questions;
    private List<GoodAnswers> goodAnswers;

    public Exame() {

    }

    public Exame(String examenTitle, String examenId, List<Question> question, List<GoodAnswers> goodAnswers) {
        this.examenTitle = examenTitle;
        this.examenId = examenId;
        this.questions = questions;
        this.goodAnswers = goodAnswers;
    }

    public String getExamenTitle() {
        return examenTitle;
    }

    public void setExamenTitle(String examenTitle) {
        this.examenTitle = examenTitle;
    }

    public String getExamenId() {
        return examenId;
    }

    public void setExamenId(String examenId) {
        this.examenId = examenId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestion(List<Question> question) {
        this.questions = question;
    }

    public List<GoodAnswers> getGoodAnswers() {
        return goodAnswers;
    }

    public void setGoodAnswers(List<GoodAnswers> goodAnswers) {
        this.goodAnswers = goodAnswers;
    }

    @Override
    public String toString() {
        return examenTitle;
    }
}

