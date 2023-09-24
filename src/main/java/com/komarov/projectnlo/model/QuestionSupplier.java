package com.komarov.projectnlo.model;

import com.komarov.projectnlo.utils.Loader;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

public class QuestionSupplier {
    private Map<Step, Question> questions;
    Loader loader;

    public QuestionSupplier(Loader loader) {
        this.questions = new EnumMap<>(Step.class);
        this.loader = loader;
        loadQuestions();
    }

    private void loadQuestions() {
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("The file is unavailable or the file location is incorrect.");
        }
        for (Step step : Step.getValuesWithoutLastValue()) {
            String questionKey = "question" + (step.ordinal() + 1);
            Map<String, String> questionData = loader.getMap().get(questionKey);
            String question = questionData.get("question");
            String answer1 = questionData.get("answer1");
            String answer2 = questionData.get("answer2");
            Question q = new Question(question, answer1, answer2);
            questions.put(step, q);
        }
    }

    public Question getQuestion(Step state){
        return questions.get(state);
    }
}
