package org.ij2c.evolution.controller;

import org.bson.types.ObjectId;
import org.ij2c.evolution.model.Client;
import org.ij2c.evolution.service.ClientService;
import org.ij2c.evolution.utils.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Inject
    ClientService clientService;

    @PostMapping("/insertClient")
    public ResponseEntity<Message> insertClient(@RequestBody Client client){
        boolean success = clientService.insertClientOnMongo(client);
        return Message.sendMessageResponseEntity(success);
    }

    @GetMapping("/getClientById/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable String id){
        Client client = clientService.getClientOnMongo(id);
        if (client == null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping("/deleteClient/{clientId}")
    public ResponseEntity<Message> deleteClient(@PathVariable ObjectId clientId){
        boolean success = clientService.deleteClient(clientId);
        return Message.sendMessageResponseEntity(success);
    }


}
