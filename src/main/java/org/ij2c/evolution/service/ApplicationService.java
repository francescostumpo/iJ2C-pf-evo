package org.ij2c.evolution.service;

import io.vertx.core.json.JsonObject;
import org.bson.types.ObjectId;
import org.ij2c.evolution.model.Application;
import org.ij2c.evolution.model.Client;
import org.ij2c.evolution.model.Question;
import org.ij2c.evolution.repository.ApplicationRepository;
import org.ij2c.evolution.repository.QuestionRepository;
import org.ij2c.evolution.repository.QuestionnaireRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ApplicationService {

    @Inject
    ApplicationRepository applicationRepository;

    @Inject
    QuestionnaireRepository questionnaireRepository;

    @Inject
    QuestionnaireService questionnaireService;

    @Inject
    QuestionService questionService;

    public boolean createApplication(Application application){

        ObjectId applicationId = applicationRepository.createApplication(application);
        if(applicationId == null){
            return false;
        }
        /*JsonObject questionnaire = new JsonObject();
        questionnaire.put("applicationId", applicationId);
        List<Question> questionList = questionService.getAllQuestionsOnMongo();
        List<JsonObject> jsonObjectList = new ArrayList<>();
        for(Question question: questionList){
            JsonObject jsonObject = new JsonObject();
            jsonObject.put("questionId", question.getId());
            jsonObject.put("answerValue", 1);
            jsonObjectList.add(jsonObject);
        }
        questionnaire.put("questionAnswerList", jsonObjectList);
        if(!questionnaireService.createQuestionnaire(questionnaire)){
            return false;
        }*/
        return true;
    }

    public Application getApplicationById(ObjectId applicationId){
        Application application = applicationRepository.getApplicationOnMongo(applicationId);
        return application;
    }

    public boolean updateApplication(ObjectId applicationId, ObjectId questionnaireId){

        boolean success = applicationRepository.updateApplication(applicationId, questionnaireId);
        return success;
    }

    public boolean updateApplication(Application application){
        boolean success = applicationRepository.updateApplication(application);
        return success;
    }

    public boolean deleteApplication(ObjectId applicationId){
        Application application = applicationRepository.findById(applicationId);
        if(application == null){
            return false;
        }
        ObjectId questionnaireId = application.getQuestionnaireId();
        boolean deleteSuccess = false;
        boolean success = questionnaireRepository.deleteQuestionnaire(questionnaireId);
        if(success){
           deleteSuccess = applicationRepository.deleteApplication(applicationId);
        }
        return deleteSuccess;
    }

    public List<Application> getApplicationsByClientId(ObjectId clientId){
        List<Application> applicationList = applicationRepository.getApplicationsByClientId(clientId);

        return applicationList;
    }

    public boolean deleteApplicationsLinkedToClient(ObjectId clientId){
        boolean success = false;
        List<Application> applicationList = applicationRepository.getApplicationsByClientId(clientId);
        try{
            for(Application application: applicationList){
                success = deleteApplication(application.getId());
                if(success == false){
                    return false;
                }
            }
            success = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }
}
