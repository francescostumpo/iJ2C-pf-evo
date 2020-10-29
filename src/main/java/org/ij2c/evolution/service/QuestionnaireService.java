package org.ij2c.evolution.service;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.bson.types.ObjectId;
import org.ij2c.evolution.model.Question;
import org.ij2c.evolution.model.Questionnaire;
import org.ij2c.evolution.repository.QuestionRepository;
import org.ij2c.evolution.repository.QuestionnaireRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class QuestionnaireService {

    @Inject
    QuestionnaireRepository questionnaireRepository;

    @Inject
    QuestionRepository questionRepository;

    public boolean createQuestionnaire(JsonObject jsonObject){
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setClientId(jsonObject.getString("clientId"));
        questionnaire.setQuestionAnswerList(jsonObject.getJsonArray("questionAnswerList").getList());
        JsonArray ja = jsonObject.getJsonArray("questionAnswerList");
        Double resultValue = calculateResultValue(ja);
        questionnaire.setResultValue(resultValue);
        String resultClause = calculateResultClause(resultValue);
        questionnaire.setResultClause(resultClause);

        boolean success = questionnaireRepository.createQuestionnaire(questionnaire);
        return true;
    }

    private String calculateResultClause(Double resultValue) {
        if(resultValue >= 0 && resultValue < 1.5){
            return "Dispose & Re-Engineer";
        }else if(resultValue >= 1.5 && resultValue < 3){
            return "Keep";
        }else if( resultValue >=3 && resultValue < 6){
            return "Migrate";
        }else{
            return "Empower";
        }
    }

    private double calculateResultValue(JsonArray questionAnswerList) {
        Double totalWeight = 0.0;
        Double questionsScore = 0.0;

        for(Object jo : questionAnswerList){
            JsonObject json = JsonObject.mapFrom(jo);
            Question question = questionRepository.findById(new ObjectId(json.getString("questionId")));
            Double questionWeight = question.getQuestionWeight();
            totalWeight = totalWeight + questionWeight;
            questionsScore = questionsScore + (json.getDouble("answerValue") * questionWeight);
        }

        Double finalScore = questionsScore / totalWeight;
        return finalScore;
    }
}
