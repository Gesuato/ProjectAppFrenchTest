package com.midterm.lasalle.projectandroidv1;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.AnswersUser;
import model.Exame;


public class ResumeAnswersUserActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener{

    final static int REQUEST_CODE = 1;

    private ListView listViewResume;
    private Button btnFinishResume;
    private TextView textViewTimerResume;
    private ArrayList<AnswersUser> answersUserArrayList = new ArrayList<AnswersUser>();
    private Exame exame = new Exame();
    private int timerExamFixe;
    private int timerExam;
    private ImageView imageViewAlert;
    private TextView textViewAlert;
    private TextView textViewExamenTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_questions);
        setContentView(R.layout.activity_resume_answers_user);

        initialize();
    }


    private void initialize() {

        btnFinishResume = findViewById(R.id.btnFinishResume);
        listViewResume = findViewById(R.id.listViewResume);
        textViewTimerResume = findViewById(R.id.textViewTimerResumeLayout);
        textViewAlert =  findViewById(R.id.textViewAlert);
        imageViewAlert = findViewById(R.id.imageViewAlert);
        textViewExamenTitle = findViewById(R.id.textViewExamenTitleResume);

        btnFinishResume.setOnClickListener(this);
        listViewResume.setOnItemClickListener(this);

        if(getIntent().getSerializableExtra("RESULT") != null){
            answersUserArrayList = (ArrayList<AnswersUser>) getIntent().getSerializableExtra("RESULT");
        }

        if(getIntent().getSerializableExtra("EXAME") != null){
            exame = (Exame) getIntent().getSerializableExtra("EXAME");
            textViewExamenTitle.setText(exame.getExamenTitle());
        }

        if(getIntent().getExtras().get("TIMER") != null){
            timerExam = (int) getIntent().getExtras().get("TIMER");
        }


        timerExamFixe = timerExam;

        AdapterCustomAnswersUserResume adapterCustomAnswersUserResume = new AdapterCustomAnswersUserResume(answersUserArrayList,this);
        listViewResume.setAdapter(adapterCustomAnswersUserResume);

        for(AnswersUser item : answersUserArrayList){
            if(item.isDoItLater() == false){
                imageViewAlert.setImageResource(R.drawable.iconalert);
                textViewAlert.setText("Cela signifie que la question n'a pas été répondue. Cliquez sur la question pour la répondre.");
                break;
            }else{
                imageViewAlert.setImageResource(android.R.color.transparent);
                textViewAlert.setText("");
            }
        }

        exameTimer();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this,doItLaterActivity.class);
        intent.putExtra("ANSWERCHANGE",answersUserArrayList);
        intent.putExtra("POSITION",position);
        intent.putExtra("TIMERCHANGE",timerExam);
        intent.putExtra("EXAMECHANGE", exame);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnFinishResume:
                Intent intent = new Intent(ResumeAnswersUserActivity.this, ResultActivity.class);
                intent.putExtra("RESULT",answersUserArrayList);
                intent.putExtra("EXAME",exame);
                startActivity(intent);
                finish();
                break;
        }
    }

    public void exameTimer() {

        final CountDownTimer countDownTimer = new CountDownTimer(timerExamFixe, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {


                timerExam = timerExam - 1000;
                textViewTimerResume.setText(new SimpleDateFormat("mm:ss").format(timerExam) + " min");


                if (timerExam <= (timerExamFixe * 0.3)) {
                    textViewTimerResume.setTextColor(Color.RED);
                }

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(ResumeAnswersUserActivity.this, ResultActivity.class);
                intent.putExtra("RESULT",answersUserArrayList);
                intent.putExtra("EXAME",exame);
                startActivity(intent);
                finish();
            }
        };
        countDownTimer.start();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){
            if (resultCode == RESULT_OK){

                answersUserArrayList = (ArrayList<AnswersUser>) data.getExtras().getSerializable("RESULTCHANGE");
                timerExam = (int) data.getExtras().get("TIMERCHANGE");

                AdapterCustomAnswersUserResume adapterCustomAnswersUserResume = new AdapterCustomAnswersUserResume(answersUserArrayList,this);
                listViewResume.setAdapter(adapterCustomAnswersUserResume);

            }
        }

        for(AnswersUser item : answersUserArrayList){
            if(item.isDoItLater() == false){
                imageViewAlert.setImageResource(R.drawable.iconalert);
                textViewAlert.setText("Cela signifie que la question n'a pas été répondue. Cliquez sur la question pour la répondre.");
                break;
            }else{
                imageViewAlert.setImageResource(android.R.color.transparent);
                textViewAlert.setText("");
            }
        }

    }
}
