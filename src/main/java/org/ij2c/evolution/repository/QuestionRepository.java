package org.ij2c.evolution.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.ij2c.evolution.model.Question;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class QuestionRepository implements PanacheMongoRepository<Question> {

    public boolean createQuestion(Question question) {

        try {
            persist(question);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
