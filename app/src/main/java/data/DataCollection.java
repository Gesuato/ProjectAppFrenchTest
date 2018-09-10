package data;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.AnswersUser;
import model.Exame;
import model.GoodAnswers;
import model.Question;

/**
 * Created by victorgesuato on 2018-04-12.
 */

public class DataCollection implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Exame> exames = new ArrayList<Exame>();
    private Exame exameSelected;

    public List<Exame> getExames() {
        return exames;
    }

    // Examens Functions
    public void setExameSelected(Exame exame) {
        exameSelected = exame;
    }

    public Exame getExameSelected() {
        return exameSelected;
    }

    public void loadExamesList(FileHandler fileHandler) throws IOException {
        exames = fileHandler.readDataExamens();
    }

    public void loadGoodAnswersList(FileHandler fileHandler) throws IOException {
        exameSelected.setGoodAnswers((List<GoodAnswers>) fileHandler.readDataGoodAnswers(exameSelected.getExamenId()));
    }

    public void loadQuestions(FileHandler fileHandler) throws IOException {
        exameSelected.setQuestion(fileHandler.readDataAnsweres(exameSelected.getExamenId()));
    }

}