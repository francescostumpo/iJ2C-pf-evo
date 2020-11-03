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
    public ResponseEntity<Object> getQuestionById(@PathVariable String id){

        Question question = questionService.getQuestionOnMongo(id);
        if(question == null){
            Message message = new Message();
            message.setMessage("No question found with questionId: " + id);
            message.setStatus("404");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @GetMapping("/getAllQuestions")
    public ResponseEntity<Object> getAllQuestions(){
        List<Question> questionList = questionService.getAllQuestionsOnMongo();
        if(questionList == null || questionList.isEmpty()){
            Message message = new Message();
            message.setMessage("No questions found");
            message.setStatus("404");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }


    @PutMapping("/updateQuestion")
    public ResponseEntity<Message> updateQuestion(@RequestBody Question question){
        boolean success = questionService.updateQuestion(question);
        return Message.sendMessageResponseEntity(success);
    }
}
