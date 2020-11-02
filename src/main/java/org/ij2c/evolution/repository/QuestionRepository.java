package org.ij2c.evolution.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.bson.types.ObjectId;
import org.ij2c.evolution.model.Application;
import org.ij2c.evolution.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class QuestionRepository implements PanacheMongoRepository<Question> {
    private Logger logger = LoggerFactory.getLogger(QuestionRepository.class);

    public boolean createQuestion(Question question) {

        try {
            persist(question);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteQuestion(ObjectId questionId){
        boolean success = false;
        try{
            deleteById(questionId);
            success = true;
        }catch (Exception e){
            logger.error("Exception in deleting question with ID " + questionId.toString() + ": " + e.getMessage());
            e.printStackTrace();
        }
        return success;
    }

}
