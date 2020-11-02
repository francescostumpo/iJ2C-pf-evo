package org.ij2c.evolution.model;

import io.quarkus.mongodb.panache.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

@MongoEntity(collection = "clients")
public class Client {

    private ObjectId id;
    private String clientName;
    private String clientReferent;
    private String clientReferentEmail;
    private String industry;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientReferent() {
        return clientReferent;
    }

    public void setClientReferent(String clientReferent) {
        this.clientReferent = clientReferent;
    }

    public String getClientReferentEmail() {
        return clientReferentEmail;
    }

    public void setClientReferentEmail(String clientReferentEmail) {
        this.clientReferentEmail = clientReferentEmail;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", clientReferent='" + clientReferent + '\'' +
                ", clientReferentEmail='" + clientReferentEmail + '\'' +
                ", industry='" + industry + '\'' +
                '}';
    }


}
