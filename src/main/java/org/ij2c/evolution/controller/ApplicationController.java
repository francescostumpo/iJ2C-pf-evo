package org.ij2c.evolution.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.bson.types.ObjectId;
import org.ij2c.evolution.model.Application;
import org.ij2c.evolution.service.ApplicationService;
import org.ij2c.evolution.service.ClientService;
import org.ij2c.evolution.utils.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Inject
    ApplicationService applicationService;

    @PostMapping("/insertApplication")
    public ResponseEntity<Message> insertApplication(@RequestBody Application application) {

        boolean success = applicationService.createApplication(application);
        return Message.sendMessageResponseEntity(success);
    }

    @GetMapping("/getApplicationsByClientId/{clientId}")
    public ResponseEntity<Object> getApplicationsByClientId(@PathVariable ObjectId clientId){
        List<Application> applicationList = applicationService.getApplicationsByClientId(clientId);
        if(applicationList.isEmpty() || applicationList == null){
            return new ResponseEntity<>(new JSONObject(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(applicationList, HttpStatus.OK);
        }
    }

    @DeleteMapping("/deleteApplication/{applicationId}")
    public ResponseEntity<Message> deleteApplication(@PathVariable ObjectId applicationId){
        boolean success = applicationService.deleteApplication(applicationId);
        return Message.sendMessageResponseEntity(success);
    }

    @PutMapping("/updateApplication")
    public ResponseEntity<Message> updateApplication(@RequestBody Application application){
        boolean success = applicationService.updateApplication(application);
        return Message.sendMessageResponseEntity(success);
    }
}
