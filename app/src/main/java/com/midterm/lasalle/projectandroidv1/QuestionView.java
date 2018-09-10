package com.midterm.lasalle.projectandroidv1;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import model.AnswersUser;
import model.Question;
import model.TypeOfQuestion;

/**
 * Created by victorgesuato on 2018-04-14.
 */

public class QuestionView extends CardView implements View.OnClickListener{

    private TextView questionTitle;
    private CheckBox checkBoxQuestion1;
    private CheckBox checkBoxQuestion2;
    private CheckBox checkBoxQuestion3;
    private CheckBox checkBoxQuestion4;
    private TextView numberQuestion;
    private TextView questionType;
    private Question currentQuestion;
    private AnswersUser currentAnswer;
    private LinearLayout layoutAnswers;



    private QuestionViewAnswersEmptyListener questionViewAnswersEmptyListener;

    public QuestionView(Context context) {
        super(context);
    }

    public QuestionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public QuestionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        inflate(getContext(), R.layout.question_view, this);


        questionTitle = findViewById(R.id.question_title);
        questionType = findViewById(R.id.question_type);
        numberQuestion = findViewById(R.id.numberQuestion);
        checkBoxQuestion1 = findViewById(R.id.checkBoxQuestion1);
        checkBoxQuestion2 = findViewById(R.id.checkBoxQuestion2);
        checkBoxQuestion3 = findViewById(R.id.checkBoxQuestion3);
        checkBoxQuestion4 = findViewById(R.id.checkBoxQuestion4);
        layoutAnswers = findViewById(R.id.layoutAnswers);


    }

    public void setCurrentAnswer(AnswersUser currentAnswer){
        this.currentAnswer = currentAnswer;
    }

    public void setCurrentQuestion(Question currentQuestion){
        this.currentQuestion = currentQuestion;
    }

    public void changeQuestion(String totalQuestion) {

        questionTitle.setText(currentQuestion.getQuestionTitle());
        checkBoxQuestion1.setText(currentQuestion.getAnswersToQuestions().get(0));
        checkBoxQuestion2.setText(currentQuestion.getAnswersToQuestions().get(1));
        checkBoxQuestion3.setText(currentQuestion.getAnswersToQuestions().get(2));
        checkBoxQuestion4.setText(currentQuestion.getAnswersToQuestions().get(3));
        checkBoxQuestion1.setOnClickListener(this);
        checkBoxQuestion2.setOnClickListener(this);
        checkBoxQuestion3.setOnClickListener(this);
        checkBoxQuestion4.setOnClickListener(this);
        numberQuestion.setText(totalQuestion);

        if (currentQuestion.getTypeOfQuestion().equals(TypeOfQuestion.single)) {
            questionType.setText("* Question à choix unique");
        } else {
            questionType.setText("* Question à choix multiples");
        }

    }

    public void clearAnswers() {
        for (int i = 0; i < layoutAnswers.getChildCount(); i++) {
            CheckBox answer = (CheckBox) layoutAnswers.getChildAt(i);
            answer.setChecked(false);
        }
    }

    public void selectAnswers(AnswersUser currentAnswerUser) {

        for (int i = 0; i < layoutAnswers.getChildCount(); i++) {
            CheckBox answer = (CheckBox) layoutAnswers.getChildAt(i);
            if (currentAnswerUser.getAnswers(i) == i + 1) {
                answer.setChecked(true);
            }

        }
    }

    public void saveSelectedAnswer(int answerNumber) {

        if (((CheckBox) layoutAnswers.getChildAt(answerNumber - 1)).isChecked()) {
            if (currentQuestion.getTypeOfQuestion() == TypeOfQuestion.single) {
                clearAnswers();
                currentAnswer.clearAnswers();
            }
            currentAnswer.setAnswers(answerNumber, answerNumber - 1);
        }else{
            currentAnswer.setAnswers(0, answerNumber - 1);
        }

        selectAnswers(currentAnswer);
    }


    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.checkBoxQuestion1:

                saveSelectedAnswer(1);
                currentAnswer.setReponse();
                changeNextTitleButton();
                break;

            case R.id.checkBoxQuestion2:

                saveSelectedAnswer(2);
                currentAnswer.setReponse();
                changeNextTitleButton();
                break;

            case R.id.checkBoxQuestion3:

                saveSelectedAnswer(3);
                currentAnswer.setReponse();
                changeNextTitleButton();
                break;

            case R.id.checkBoxQuestion4:

                saveSelectedAnswer(4);
                currentAnswer.setReponse();
                changeNextTitleButton();
                break;
        }

    }

    public void changeNextTitleButton() {

        if (currentAnswer.isDoItLater()) {
            questionViewAnswersEmptyListener.onAnswersEmpty(false);
        } else {
            questionViewAnswersEmptyListener.onAnswersEmpty(true);

        }

    }

    interface QuestionViewAnswersEmptyListener {
        void onAnswersEmpty(boolean empty);
    }

    public void setOnQuestionViewAnswersEmptyListener(QuestionViewAnswersEmptyListener questionViewAnswersEmptyListener) {
        this.questionViewAnswersEmptyListener = questionViewAnswersEmptyListener;
    }

}
