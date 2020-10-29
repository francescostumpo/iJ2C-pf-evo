package org.ij2c.evolution.controller;

import io.quarkus.mongodb.panache.PanacheQuery;
import org.ij2c.evolution.model.Question;
import org.ij2c.evolution.service.QuestionService;
import org.ij2c.evolution.utils.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Inject
    QuestionService questionService;


    @PostMapping("/insertQuestion")
    public ResponseEntity<Message> insertQuestion(@RequestBody Question question){

        boolean success = questionService.insertQuestionOnMongo(question);
        return Message.sendMessageResponseEntity(success);
    }

    @GetMapping("/getQuestionById/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable String id){

        Question question = questionService.getQuestionOnMongo(id);
        if(question == null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @GetMapping("/getAllQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        List<Question> questionList = questionService.getAllQuestionsOnMongo();
        if(questionList == null || questionList.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }
}
