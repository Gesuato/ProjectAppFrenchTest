package com.midterm.lasalle.projectandroidv1;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import model.AnswersUser;

/**
 * Created by victorgesuato on 2018-04-16.
 */

public class AdapterCustomAnswersUserResume extends BaseAdapter{

    private final List<AnswersUser> answersUserList;
    private final Activity activity;

    public AdapterCustomAnswersUserResume(List<AnswersUser> answersUser, Activity activity) {
        this.answersUserList = answersUser;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return answersUserList.size();
    }

    @Override
    public Object getItem(int position) {
        return answersUserList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return answersUserList.get(position).getIdQuestion();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = activity.getLayoutInflater().inflate(R.layout.listview_custom,parent,false);


        AnswersUser answersUser = answersUserList.get(position);

        TextView textViewIdQuestion = (TextView) view.findViewById(R.id.textViewIdQuestionResume);
        ImageView imageViewResume = (ImageView) view.findViewById(R.id.imageViewResume);

        textViewIdQuestion.setText("Question : " + Integer.toString(answersUser.getIdQuestion() + 1));

        if(answersUser.isDoItLater() == false){
            imageViewResume.setImageResource(R.drawable.iconalert);
        }else{
            imageViewResume.setImageResource(R.drawable.saveicon);
        }

        return view;
    }
}
