package org.ij2c.evolution.controller;

import net.sf.json.JSONObject;
import io.vertx.core.json.JsonObject;
import org.ij2c.evolution.service.QuestionnaireService;
import org.ij2c.evolution.utils.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
