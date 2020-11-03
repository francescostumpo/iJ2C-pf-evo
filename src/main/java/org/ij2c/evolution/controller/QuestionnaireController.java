package org.ij2c.evolution.controller;

import net.sf.json.JSONObject;
import io.vertx.core.json.JsonObject;
import org.bson.types.ObjectId;
import org.ij2c.evolution.model.Questionnaire;
import org.ij2c.evolution.service.QuestionnaireService;
import org.ij2c.evolution.utils.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("/api/questionnaires")
public class QuestionnaireController {

    @Inject
    QuestionnaireService questionnaireService;

    @PostMapping("/insertQuestionnaire")
    public ResponseEntity<Message> insertQuestionnaire(@RequestBody JSONObject jo){
        JsonObject jsonObject = JsonObject.mapFrom(jo);
        boolean success = questionnaireService.createQuestionnaire(jsonObject);
        return Message.sendMessageResponseEntity(success);
    }

    @GetMapping("/getQuestionnaireByApplicationId/{applicationId}")
    public ResponseEntity<Object> getQuestionnaireByApplicationId(@PathVariable ObjectId applicationId){
        Questionnaire questionnaire = questionnaireService.getQuestionnaireByApplicationId(applicationId);
        if(questionnaire == null){
            return new ResponseEntity<>(new JSONObject(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(questionnaire, HttpStatus.OK);
        }
    }


    @DeleteMapping("/deleteQuestionnaire/{questionnaireId}")
    public ResponseEntity<Message> deleteQuestionnaire(@PathVariable ObjectId questionnaireId){
        boolean success = questionnaireService.deleteQuestionnaire(questionnaireId);
        return Message.sendMessageResponseEntity(success);
    }

    @PutMapping("/updateQuestionnaire")
    public ResponseEntity<Message> updateQuestionnaire(@RequestBody Questionnaire questionnaire){
        boolean success = questionnaireService.updateQuestionnaire(questionnaire);
        return Message.sendMessageResponseEntity(success);
    }
}
