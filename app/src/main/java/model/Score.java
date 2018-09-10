package model;

import java.text.DecimalFormat;

/**
 * Created by victorgesuato on 2018-04-08.
 */

public class Score {

    private String idExamen;
    private double numberOfQuestion;
    private double numberOfGoodAnswers;
    private double scorePorcentage;
    DecimalFormat format = new DecimalFormat("#.##");

    public Score() {

    }

    public Score(String idExamen, double numberOfQuestion, double numberOfGoodAnswers, double scorePorcentage) {
        this.idExamen = idExamen;
        this.numberOfQuestion = numberOfQuestion;
        this.numberOfGoodAnswers = numberOfGoodAnswers;
        this.scorePorcentage = scorePorcentage;
    }

    public String getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(String idExamen) {
        this.idExamen = idExamen;
    }

    public double getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(double numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public double getNumberOfGoodAnswers() {
        return numberOfGoodAnswers;
    }

    public void setNumberOfGoodAnswers(double numberOfGoodAnswers) {
        this.numberOfGoodAnswers = numberOfGoodAnswers;
    }

    public double getScorePorcentage() {
        return scorePorcentage;
    }

    public void setScorePorcentage(double scorePorcentage) {
        this.scorePorcentage = scorePorcentage;
    }

    public double scorePorcentage(){

        scorePorcentage = (numberOfGoodAnswers / numberOfQuestion) * 100;

        scorePorcentage = Double.valueOf(format.format(scorePorcentage));

        return scorePorcentage;
    }

    @Override
    public String toString() {
        return "Score{" +
                "idExamen=" + idExamen +
                ", numberOfQuestion=" + numberOfQuestion +
                ", numberOfGoodAnswers=" + numberOfGoodAnswers +
                ", scorePorcentage=" + scorePorcentage +
                '}';
    }
}
