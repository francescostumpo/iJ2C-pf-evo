package org.ij2c.evolution.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Message {
    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResponseEntity<Message> sendMessageResponseEntity(boolean success) {
        Message message = new Message();
        if(success){
            message.setMessage("Operation successfully completed on MongoDB");
            message.setStatus("200");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }else{
            message.setMessage("Failed operation on MongoDB");
            message.setStatus("500");
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
