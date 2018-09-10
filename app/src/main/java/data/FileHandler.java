package data;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import model.Exame;
import model.GoodAnswers;
import model.Question;
import model.TypeOfQuestion;

/**
 * Created by victorgesuato on 2018-04-05.
 */

public class FileHandler {

    private AssetManager assetManager;

    public FileHandler(AssetManager assetManager){
        this.assetManager = assetManager;
    }

    public List<Question> readDataAnsweres(String idExamen) throws IOException {

        List<Question> listFromFile = new ArrayList<>();
        String line = null;

        StringTokenizer myTokenizer = null;

        BufferedReader bufferedReader = readFromFile(assetManager.open(idExamen + ".txt"));

        while ((line = bufferedReader.readLine()) != null) {
            Question question = new Question();
            List<String> answers = new ArrayList<>();

            myTokenizer = new StringTokenizer(line, "|");

            question.setIdQuestion(Integer.parseInt(myTokenizer.nextToken()));

            if (myTokenizer.nextToken().equalsIgnoreCase("multiple")) {
                question.setTypeOfQuestion(TypeOfQuestion.multiple);
            } else {
                question.setTypeOfQuestion(TypeOfQuestion.single);
            }

            question.setQuestion(myTokenizer.nextToken());

            for (int i = 0; i < 4; i++) {
                answers.add(myTokenizer.nextToken());
            }

            question.setAnswersToQuestions(answers);

            System.out.println(question.getAnswersToQuestions());

            listFromFile.add(question);
        }
        bufferedReader.close();
        return listFromFile;

    }

    public List<Exame> readDataExamens() throws IOException {

        String line = null;

        StringTokenizer myTokenizer = null;

        List<Exame> listFromFile = new ArrayList<Exame>();

        BufferedReader bufferedReader = readFromFile(assetManager.open("Examens.txt"));

        while ((line = bufferedReader.readLine()) != null) {
            Exame exame = new Exame();


            myTokenizer = new StringTokenizer(line, "|");

            exame.setExamenId(myTokenizer.nextToken());
            exame.setExamenTitle(myTokenizer.nextToken());

            listFromFile.add(exame);
        }

        bufferedReader.close();

        return listFromFile;
    }

    public List<GoodAnswers> readDataGoodAnswers(String idExamen) throws IOException {

        String line = null;
        StringTokenizer myTokenizer = null;

        List<GoodAnswers> listFromFile = new ArrayList<GoodAnswers>();

        BufferedReader bufferedReader = readFromFile(assetManager.open(idExamen + "GoodAnswers.txt"));


        while ((line = bufferedReader.readLine()) != null) {
            GoodAnswers goodAnswers = new GoodAnswers();

            myTokenizer = new StringTokenizer(line, "|");

            goodAnswers.setIdQuestion(Integer.parseInt(myTokenizer.nextToken()));
            goodAnswers.setGoodAnswer(myTokenizer.nextToken());

            listFromFile.add(goodAnswers);
        }
        bufferedReader.close();
        return listFromFile;
    }

    private BufferedReader readFromFile(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;
        inputStreamReader = new InputStreamReader(inputStream);

        BufferedReader bufferedReader = null;
        bufferedReader = new BufferedReader(inputStreamReader);

        return bufferedReader;
    }
}