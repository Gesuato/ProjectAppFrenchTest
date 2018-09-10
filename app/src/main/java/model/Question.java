package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victorgesuato on 2018-04-04.
 */

public class Question implements Serializable {

    private int idQuestion;
    private String questionTitle;
    private TypeOfQuestion typeOfQuestion;
    private List<String> answersToQuestions = new ArrayList<>();
    private GoodAnswers goodAnswers;

    public Question() {
    }

    public Question(int idQuestion, String questionTitle, TypeOfQuestion typeOfQuestion, List<String> answersToQuestions, GoodAnswers goodAnswers) {
        this.idQuestion = idQuestion;
        this.questionTitle = questionTitle;
        this.typeOfQuestion = typeOfQuestion;
        this.answersToQuestions = answersToQuestions;
        this.goodAnswers = goodAnswers;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestion(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public TypeOfQuestion getTypeOfQuestion() {
        return typeOfQuestion;
    }

    public void setTypeOfQuestion(TypeOfQuestion typeOfQuestion) {
        this.typeOfQuestion = typeOfQuestion;
    }

    public List<String> getAnswersToQuestions() {
        return answersToQuestions;
    }

    public void setAnswersToQuestions(List<String> answersToQuestions) {
        this.answersToQuestions = answersToQuestions;
    }

    public GoodAnswers getGoodAnswers() {
        return goodAnswers;
    }

    public void setGoodAnswers(GoodAnswers goodAnswers) {
        this.goodAnswers = goodAnswers;
    }

    @Override
    public String toString() {
        return questionTitle;
    }



}