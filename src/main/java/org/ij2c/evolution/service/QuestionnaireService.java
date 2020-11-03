package org.ij2c.evolution.service;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import net.sf.json.JSONArray;
import org.bson.types.ObjectId;
import org.ij2c.evolution.model.Application;
import org.ij2c.evolution.model.Question;
import org.ij2c.evolution.model.Questionnaire;
import org.ij2c.evolution.model.support.QuestionAnswer;
import org.ij2c.evolution.repository.QuestionRepository;
import org.ij2c.evolution.repository.QuestionnaireRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class QuestionnaireService {

    @Inject
    QuestionnaireRepository questionnaireRepository;

    @Inject
    QuestionRepository questionRepository;

    @Inject
    ApplicationService applicationService;

    public boolean createQuestionnaire(JsonObject jsonObject){
        Questionnaire questionnaire = new Questionnaire();
        String applicationId = jsonObject.getString("applicationId");
        List<QuestionAnswer> questionAnswerList = convertJsonArrayToList(jsonObject.getJsonArray("questionAnswerList"));
        questionnaire.setQuestionAnswerList(questionAnswerList);
        questionnaire = calculateModernizationStrategy(questionnaire);

        ObjectId questionnaireId = questionnaireRepository.createQuestionnaire(questionnaire);
        if(questionnaireId == null){
            return false;
        }
        boolean success = applicationService.updateApplication(new ObjectId(applicationId), questionnaireId);
        return success;
    }

    private List<QuestionAnswer> convertJsonArrayToList(JsonArray ja) {
        List<QuestionAnswer> questionAnswers = new ArrayList<>();
        for(Object ob: ja){
            JsonObject jo = JsonObject.mapFrom(ob);
            QuestionAnswer questionAnswer = new QuestionAnswer();
            questionAnswer.setQuestionId(jo.getString("questionId"));
            questionAnswer.setAnswerValue(jo.getDouble("answerValue"));
            questionAnswers.add(questionAnswer);
        }
        return questionAnswers;
    }

    private Questionnaire calculateModernizationStrategy(Questionnaire questionnaire) {
        List<QuestionAnswer> qa = questionnaire.getQuestionAnswerList();
        Double resultValue = calculateResultValue(qa);
        questionnaire.setResultValue(resultValue);
        String resultClause = calculateResultClause(resultValue);
        questionnaire.setResultClause(resultClause);
        return questionnaire;
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

    private double calculateResultValue(List<QuestionAnswer> questionAnswerList) {
        Double totalWeight = 0.0;
        Double questionsScore = 0.0;


        for(QuestionAnswer qa : questionAnswerList){
            Question question = questionRepository.findById(new ObjectId(qa.getQuestionId()));
            Double questionWeight = question.getQuestionWeight();
            totalWeight = totalWeight + questionWeight;
            questionsScore = questionsScore + (qa.getAnswerValue() * questionWeight);
        }

        Double finalScore = questionsScore / totalWeight;
        return finalScore;
    }

    public boolean deleteQuestionnaire(ObjectId questionnaireId){
        boolean success = questionnaireRepository.deleteQuestionnaire(questionnaireId);
        return success;
    }

    public Questionnaire getQuestionnaireByApplicationId(ObjectId applicationId) {
        Application application = applicationService.getApplicationById(applicationId);
        if(application == null){
            return null;
        }
        Questionnaire questionnaire = questionnaireRepository.getQuestionnaireOnMongo(application.getQuestionnaireId());
        return questionnaire;
    }

    public boolean updateQuestionnaire(Questionnaire questionnaire) {
        try{
            questionnaire = calculateModernizationStrategy(questionnaire);
            questionnaireRepository.updateQuestionnaire(questionnaire);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
