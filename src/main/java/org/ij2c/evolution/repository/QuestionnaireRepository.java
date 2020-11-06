package org.ij2c.evolution.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.bson.types.ObjectId;
import org.ij2c.evolution.model.Questionnaire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuestionnaireRepository implements PanacheMongoRepository<Questionnaire> {

    private Logger logger = LoggerFactory.getLogger(QuestionRepository.class);

    public ObjectId updateOrCreateQuestionnaire(Questionnaire questionnaire){
        ObjectId questionnaireId = questionnaire.getId();
        try{
            if(questionnaireId == null){
                persist(questionnaire);

            }else{
                update(questionnaire);
            }
            questionnaireId = questionnaire.getId();
        }catch (Exception e){
            e.printStackTrace();
        }
        return questionnaireId;
    }

    public Questionnaire getQuestionnaireOnMongo(ObjectId questionnaireId){
        Questionnaire questionnaire = findById(questionnaireId);
        return questionnaire;
    }

    public boolean deleteQuestionnaire(ObjectId questionnaireId){
        boolean success = false;
        try{
            deleteById(questionnaireId);
            success = true;
        }catch (Exception e){
            logger.error("Exception in deleting questionnaire with ID " + questionnaireId.toString() + ": " + e.getMessage());
            e.printStackTrace();
        }
        return success;
    }

    public boolean updateQuestionnaire(Questionnaire questionnaire) {
        boolean success = false;
        try{
            update(questionnaire);
            success = true;
        }catch (Exception e){
            logger.error("Exception in updating questionnaire with ID " + questionnaire.getId().toString() + ": " + e.getMessage());
            e.printStackTrace();
        }
        return success;
    }
}
