package org.ij2c.evolution.controller;

import net.sf.json.JSONObject;
import org.bson.types.ObjectId;
import org.ij2c.evolution.model.Client;
import org.ij2c.evolution.service.ClientService;
import org.ij2c.evolution.utils.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

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

    @GetMapping("/getAllClients")
    public ResponseEntity<Object> getAllClients(){
        List<Client> clientList = clientService.getAllClients();
        if(clientList.isEmpty() || clientList == null){
            return new ResponseEntity<>(new JSONObject(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(clientList, HttpStatus.OK);
        }
    }

    @GetMapping("/getClientById/{id}")
    public ResponseEntity<Object> getClientById(@PathVariable String id){
        Client client = clientService.getClientOnMongo(id);
        if (client == null){
            Message message = new Message();
            message.setMessage("No client found with clientId: " + id);
            message.setStatus("404");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping("/deleteClient/{clientId}")
    public ResponseEntity<Message> deleteClient(@PathVariable ObjectId clientId){
        boolean success = clientService.deleteClient(clientId);
        return Message.sendMessageResponseEntity(success);
    }

    @PutMapping("/updateClient")
    public ResponseEntity<Message> updateClient(@RequestBody Client client){
        boolean success = clientService.updateClient(client);
        return Message.sendMessageResponseEntity(success);
    }


}
