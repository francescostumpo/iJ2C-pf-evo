package org.ij2c.evolution.model;

import io.quarkus.mongodb.panache.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "applications")
public class Application {

    private ObjectId id;
    private String applicationName;
    private String applicationDomain;
    private String applicationReferent;
    private String applicationReferentEmail;
    private ObjectId questionnaireId;
    private ObjectId clientId;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationDomain() {
        return applicationDomain;
    }

    public void setApplicationDomain(String applicationDomain) {
        this.applicationDomain = applicationDomain;
    }

    public String getApplicationReferent() {
        return applicationReferent;
    }

    public void setApplicationReferent(String applicationReferent) {
        this.applicationReferent = applicationReferent;
    }

    public String getApplicationReferentEmail() {
        return applicationReferentEmail;
    }

    public void setApplicationReferentEmail(String applicationReferentEmail) {
        this.applicationReferentEmail = applicationReferentEmail;
    }


    public ObjectId getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(ObjectId questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", applicationName='" + applicationName + '\'' +
                ", applicationDomain='" + applicationDomain + '\'' +
                ", applicationReferent='" + applicationReferent + '\'' +
                ", applicationReferentEmail='" + applicationReferentEmail + '\'' +
                ", questionnaireId=" + questionnaireId +
                ", clientId=" + clientId +
                '}';
    }

    public ObjectId getClientId() {
        return clientId;
    }

    public void setClientId(ObjectId clientId) {
        this.clientId = clientId;
    }

}
