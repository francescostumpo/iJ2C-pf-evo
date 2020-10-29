package org.ij2c.evolution.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.ij2c.evolution.model.Questionnaire;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuestionnaireRepository implements PanacheMongoRepository<Questionnaire> {

    public boolean createQuestionnaire(Questionnaire questionnaire){
        try{
            persist(questionnaire);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
