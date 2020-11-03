package org.ij2c.evolution.service;

import io.quarkus.panache.common.Sort;
import org.bson.types.ObjectId;
import org.ij2c.evolution.model.Question;
import org.ij2c.evolution.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class QuestionService {

    @Inject
    QuestionRepository questionRepository;

    private Logger logger = LoggerFactory.getLogger(QuestionService.class);

    public boolean insertQuestionOnMongo(Question question){
        boolean inserted = questionRepository.createQuestion(question);
        return inserted;
    }

    public Question getQuestionOnMongo(String id){
        Question question = questionRepository.findById(new ObjectId(id));
        return question;
    }

    public List<Question>getAllQuestionsOnMongo(){
        List<Question> questionList = questionRepository.listAll(Sort.ascending("questionSequence"));
        return questionList;
    }

    public boolean updateQuestion(Question question) {
        boolean success = questionRepository.updateQuestion(question);
        return success;
    }
}
