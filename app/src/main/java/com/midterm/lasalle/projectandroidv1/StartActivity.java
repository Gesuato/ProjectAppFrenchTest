package com.midterm.lasalle.projectandroidv1;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import data.DataCollection;
import data.FileHandler;
import model.Exame;

public class StartActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener{

    private ArrayAdapter<String> adapter;
    private List<String> arrayfileTxtExamens;
    private Spinner spinnerExamens;
    private Button btnStart, btnFinish;
    private DataCollection dataCollection = new DataCollection();
    private FileHandler fileHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        initialize();
    }

    private void initialize(){

        fileHandler = new FileHandler(getResources().getAssets());

        try {
            dataCollection.loadExamesList(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        arrayfileTxtExamens = new ArrayList<String>();

        for(Exame item : dataCollection.getExames()){
            arrayfileTxtExamens.add(item.getExamenTitle());
        }

        spinnerExamens = (Spinner) findViewById(R.id.spinnerExamens);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnFinish = (Button) findViewById(R.id.btnFinish);

        btnStart.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

        adapter = new ArrayAdapter<String>(this,R.layout.spinner_startlayout,arrayfileTxtExamens);
        spinnerExamens.setAdapter(adapter);
        spinnerExamens.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        dataCollection.setExameSelected(dataCollection.getExames().get(position));

        try {
            dataCollection.loadGoodAnswersList(fileHandler);
            dataCollection.loadQuestions(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this,QuestionsActivity.class);
        intent.putExtra("EXAM", dataCollection.getExameSelected());

        switch (view.getId()){

            case R.id.btnStart:
                startActivity(intent);
                finish();
                break;

            case R.id.btnFinish:
                finish();
                break;
        }

    }
}