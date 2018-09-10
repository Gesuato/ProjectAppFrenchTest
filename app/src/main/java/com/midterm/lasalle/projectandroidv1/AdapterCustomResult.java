package com.midterm.lasalle.projectandroidv1;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.AnswersUser;
import model.Exame;

/**
 * Created by victorgesuato on 2018-04-17.
 */

public class AdapterCustomResult extends BaseAdapter {


    private final Activity activity;
    private final List<AnswersUser> answersUsersList;
    private final Exame exame;

    public AdapterCustomResult(Activity activity, List<AnswersUser> answersUsersList, Exame exame) {
        this.activity = activity;
        this.answersUsersList = answersUsersList;
        this.exame = exame;
    }

    @Override
    public int getCount() {
        return answersUsersList.size();
    }

    @Override
    public Object getItem(int position) {
        return answersUsersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return answersUsersList.get(position).getIdQuestion();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = activity.getLayoutInflater().inflate(R.layout.listview_result,parent,false);

        AnswersUser answersUser = answersUsersList.get(position);


        TextView textViewQuestion = (TextView) view.findViewById(R.id.textViewIdQuestionResultListView);
        ImageView imageViewResult = (ImageView) view.findViewById(R.id.imageViewResult);
        textViewQuestion.setText("Question : " + Integer.toString(answersUser.getIdQuestion() + 1));

        imageViewResult.setImageResource(R.drawable.erroricon);

            if(answersUser.getIdQuestion() == exame.getGoodAnswers().get(position).getIdQuestion()){
                if(answersUser.getReponse().equalsIgnoreCase(exame.getGoodAnswers().get(position).getGoodAnswer())){
                    imageViewResult.setImageResource(R.drawable.iconok);
                }
            }



        return view;
    }
}
