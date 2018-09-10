package com.midterm.lasalle.projectandroidv1;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.DataCollection;
import data.FileHandler;
import model.AnswersUser;
import model.Exame;
import model.Score;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnFinishResult,btnReturnExamen;
    private ListView listViewQuestionResult;
    private TextView textViewTileExamen,textViewTotalScore;
    private List<AnswersUser> answersUserList = new ArrayList<>();
    private Score score;
    private String resultTextViewString;
    private Exame exame = new Exame();
    private int numberQuestionsCorrect = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_result);

        initialize();
    }

    private void initialize() {

        exame = (Exame) getIntent().getSerializableExtra("EXAME");

        answersUserList = (List<AnswersUser>) getIntent().getSerializableExtra("RESULT");

        score = new Score();
        listViewQuestionResult = (ListView) findViewById(R.id.listViewQuestion);
        btnFinishResult = (Button) findViewById(R.id.btnFinishResult);
        btnReturnExamen = (Button) findViewById(R.id.btnReturnExamen);
        textViewTileExamen = (TextView) findViewById(R.id.textViewTitle);
        textViewTotalScore = (TextView) findViewById(R.id.textViewTotalScore);
        btnFinishResult.setOnClickListener(this);
        btnReturnExamen.setOnClickListener(this);

        String titleAndId = "Résultat de " + exame.getExamenId() + ": " + exame.getExamenTitle();
        textViewTileExamen.setText(titleAndId);

        AdapterCustomResult adapterCustomResult = new AdapterCustomResult(this,answersUserList,exame);
        listViewQuestionResult.setAdapter(adapterCustomResult);


        for(int i = 0;i < answersUserList.size();i++){

            System.out.println(answersUserList.get(i).getReponse());

            if(answersUserList.get(i).getIdQuestion() == exame.getGoodAnswers().get(i).getIdQuestion()){
                if(answersUserList.get(i).getReponse().equalsIgnoreCase(exame.getGoodAnswers().get(i).getGoodAnswer())){
                    numberQuestionsCorrect++;
                }
            }
        }
        score.setIdExamen(exame.getExamenId());
        score.setNumberOfQuestion(exame.getGoodAnswers().size());
        score.setNumberOfGoodAnswers(numberQuestionsCorrect);

        if(score.scorePorcentage() >= 60){
            resultTextViewString = "Score Total: " + Double.toString(score.scorePorcentage()) + "%, \nFélicitations vous passez l'examen " + score.getIdExamen();
            textViewTotalScore.setTextColor(Color.parseColor("#298A08"));
        }else{
            resultTextViewString = "Score Total: " + Double.toString(score.scorePorcentage()) + "%, \nMalheureusement, vous avez échoué l'examen " + score.getIdExamen();
            textViewTotalScore.setTextColor(Color.parseColor("#DF0101"));
        }
        textViewTotalScore.setText(resultTextViewString);

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(ResultActivity.this,StartActivity.class);
        switch (view.getId()){
            case R.id.btnFinishResult:
                finish();
                break;

            case R.id.btnReturnExamen:
                startActivity(intent);
                finish();
                break;
        }
    }
}
