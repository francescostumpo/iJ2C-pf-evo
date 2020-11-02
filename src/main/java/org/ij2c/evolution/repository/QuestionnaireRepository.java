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

    public ObjectId createQuestionnaire(Questionnaire questionnaire){
        ObjectId questionnaireId = null;
        try{
            persist(questionnaire);
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
            logger.error("Exception in deleting application with ID " + questionnaireId.toString() + ": " + e.getMessage());
            e.printStackTrace();
        }
        return success;
    }
}
