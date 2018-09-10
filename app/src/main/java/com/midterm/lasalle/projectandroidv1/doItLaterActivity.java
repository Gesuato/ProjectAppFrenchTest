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


public class doItLaterActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<AnswersUser> answersUsers = new ArrayList<AnswersUser>();
    private Exame exame;
    private int position;
    private int timerExam;
    private int timerExamFixe;
    private TextView textViewTimer;
    private Button btnNext;
    private TextView textViewTitle;
    private String totalQuestions;
    private AnswersUser currenAnswerUser;
    private Question currentQuestion;
    private QuestionView questionView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_questions);
        setContentView(R.layout.activity_do_it_later);

        initialize();
    }

    private void initialize() {

        textViewTimer =  findViewById(R.id.textViewTimerDoItLater);
        textViewTitle = findViewById(R.id.textViewExamenTitleDoiTLater);
        btnNext = (Button) findViewById(R.id.btnNextDoItLater);
        btnNext.setOnClickListener(this);

        questionView = findViewById(R.id.question_view);

        exame = new Exame();
        answersUsers = (ArrayList<AnswersUser>) getIntent().getExtras().getSerializable("ANSWERCHANGE");
        exame = (Exame) getIntent().getExtras().getSerializable("EXAMECHANGE");
        position = (int) getIntent().getExtras().get("POSITION");
        timerExam = (int) getIntent().getExtras().get("TIMERCHANGE");
        timerExamFixe = timerExam;

        currenAnswerUser = answersUsers.get(position);
        currentQuestion = exame.getQuestions().get(position);

        questionView.setCurrentQuestion(currentQuestion);
        questionView.setCurrentAnswer(currenAnswerUser);

        textViewTitle.setText(exame.getExamenTitle());

        setAnswersOfQuestionCheckBox();
        exameTimer();
        setReponseUserCheckBox();
        changeNextTitleButton();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnNextDoItLater:
                Intent intent = new Intent();

                intent.putExtra("RESULTCHANGE",answersUsers);
                intent.putExtra("POSITION", position);
                intent.putExtra("TIMERCHANGE",timerExam);
                setResult(RESULT_OK, intent);
                finish();

                break;
        }
    }


    public void exameTimer() {

        final CountDownTimer countDownTimer = new CountDownTimer(timerExamFixe, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {


                timerExam = timerExam - 1000;
                textViewTimer.setText(new SimpleDateFormat("mm:ss").format(timerExam) + " min");


                if (timerExam <= (timerExamFixe * 0.3)) {
                    textViewTimer.setTextColor(Color.RED);
                }

            }

            @Override
            public void onFinish() {
                finish();
            }
        };
        countDownTimer.start();
    }

    public void setAnswersOfQuestionCheckBox() {

        totalQuestions = Integer.toString(position + 1) + "/" + Integer.toString(answersUsers.size());
        questionView.changeQuestion(totalQuestions);
    }

    public void setReponseUserCheckBox() {
        questionView.selectAnswers(currenAnswerUser);

    }

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
}