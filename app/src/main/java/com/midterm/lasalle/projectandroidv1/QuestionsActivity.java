package com.midterm.lasalle.projectandroidv1;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.AnswersUser;
import model.Exame;
import model.Question;


public class QuestionsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnNext, btnReturn;
    private TextView textViewExamenTitle;
    private TextView textViewTimer;
    private QuestionView questionView;
    private int contQuestion;
    private String totalQuestions;
    private ArrayList<AnswersUser> answersUserArrayList;
    private int contNextPage;
    private int timerExamen;
    private int timerExamenMiliseconds;
    private Exame exameSelected;
    private AnswersUser currentAnswer;
    private Question currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_questions);

        initialize();

    }

    private void initialize() {



        exameSelected = (Exame) getIntent().getSerializableExtra("EXAM");
        answersUserArrayList = new ArrayList<>();

        for (int i = 0; i < exameSelected.getQuestions().size(); i++) {
            AnswersUser answersUser = new AnswersUser();
            answersUser.setIdQuestion(exameSelected.getQuestions().get(i).getIdQuestion());
            answersUser.setQuestion(exameSelected.getQuestions().get(i).getQuestionTitle());
            answersUserArrayList.add(answersUser);
        }


        currentAnswer = answersUserArrayList.get(contQuestion);
        currentQuestion = exameSelected.getQuestions().get(contQuestion);

        contQuestion = 0;
        contNextPage = 0;

        questionView = findViewById(R.id.question_view);
        textViewExamenTitle = findViewById(R.id.textViewExamenTitle);
        textViewTimer = (TextView) findViewById(R.id.textViewTimer);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnReturn = (Button) findViewById(R.id.btnReturn);


        btnNext.setOnClickListener(this);
        btnReturn.setOnClickListener(this);
        btnReturn.setEnabled(false);


        textViewExamenTitle.setText(exameSelected.getExamenTitle());

        timerExamenMiliseconds = exameSelected.getQuestions().size() * 800000;
        timerExamen = exameSelected.getQuestions().size() * 800000;

        questionView.setCurrentAnswer(currentAnswer);
        questionView.setCurrentQuestion(currentQuestion);

        setAnswersOfQuestionCheckBox();
        exameTimer();
        changeNextTitleButton();

    }

    @Override
    public void onClick(View v) {

//        Intent intent = new Intent(this, ResultActivity.class);

        Intent intent = new Intent(this, ResumeAnswersUserActivity.class);
        intent.putExtra("RESULT", answersUserArrayList);
        intent.putExtra("EXAME", exameSelected);
        intent.putExtra("TIMER", timerExamen);

        switch (v.getId()) {

            case R.id.btnNext:

                contNextPage++;

                questionView.clearAnswers();
                if (contQuestion != exameSelected.getQuestions().size() - 1) {
                    contQuestion++;
                }

                currentQuestion = exameSelected.getQuestions().get(contQuestion);
                currentAnswer = answersUserArrayList.get(contQuestion);
                questionView.setCurrentAnswer(currentAnswer);
                questionView.setCurrentQuestion(currentQuestion);

                if (contNextPage == exameSelected.getQuestions().size()) {
                    startActivity(intent);
                    finish();
                }

                if (contQuestion == exameSelected.getQuestions().size() - 1) {
                    btnNext.setText("Résultat");

                }

                setReponseUserCheckBox();
                setAnswersOfQuestionCheckBox();
                questionView.changeNextTitleButton();
                btnReturn.setEnabled(true);

                break;

            case R.id.btnReturn:

                contQuestion--;
                contNextPage--;
                questionView.clearAnswers();
                currentQuestion = exameSelected.getQuestions().get(contQuestion);
                currentAnswer = answersUserArrayList.get(contQuestion);
                questionView.setCurrentAnswer(currentAnswer);
                questionView.setCurrentQuestion(currentQuestion);

                if (contQuestion == 0) {
                    btnReturn.setEnabled(false);
                }

                setAnswersOfQuestionCheckBox();
                setReponseUserCheckBox();
                questionView.changeNextTitleButton();

                break;
        }
    }

    public void exameTimer() {

        final CountDownTimer countDownTimer = new CountDownTimer(timerExamenMiliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {


                timerExamen = timerExamen - 1000;
                textViewTimer.setText(new SimpleDateFormat("mm:ss").format(timerExamen) + " min");


                if (timerExamen <= (timerExamenMiliseconds * 0.3)) {
                    textViewTimer.setTextColor(Color.RED);
                }


            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(QuestionsActivity.this, ResultActivity.class);
                intent.putExtra("RESULT", answersUserArrayList);
                intent.putExtra("EXAME", exameSelected);
                startActivity(intent);
                finish();
            }
        };
        countDownTimer.start();
    }


    /// interface

    public void changeNextTitleButton() {

        questionView.setOnQuestionViewAnswersEmptyListener(new QuestionView.QuestionViewAnswersEmptyListener() {
            @Override
            public void onAnswersEmpty(boolean empty) {

                if(empty) {
                    btnNext.setText("Faire Plus Tard");
                } else {
                    btnNext.setText("Suivant");
                    }
            }
        });

        }


    public void setAnswersOfQuestionCheckBox() {

        totalQuestions = Integer.toString(contQuestion + 1) + "/" + Integer.toString(answersUserArrayList.size());
        questionView.changeQuestion(totalQuestions);
    }

    public void setReponseUserCheckBox() {

        AnswersUser currenAnswerUser = answersUserArrayList.get(contQuestion);
        questionView.selectAnswers(currenAnswerUser);

    }


}
