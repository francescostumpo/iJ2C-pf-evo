package org.acme.spring.web;

import org.ij2c.evolution.model.Question;
import org.ij2c.evolution.service.ClientService;
import org.ij2c.evolution.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/api")
public class ExampleController {

    @Inject
    ClientService clientService;

    @Inject
    QuestionService questionService;

    @GetMapping("/hello")
    public String hello() {
        return "hello there!";
    }


}
