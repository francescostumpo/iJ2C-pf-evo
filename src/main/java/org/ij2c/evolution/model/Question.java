package org.ij2c.evolution.model;

import io.quarkus.mongodb.panache.MongoEntity;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.ij2c.evolution.model.support.Option;

import java.util.List;

@MongoEntity(collection = "questions")
public class Question {

    private ObjectId id;
    private int questionSequence;
    private String questionTenant;
    private String questionClause;
    private String questionTooltip;
    private double questionWeight;
    private List<Option> options;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getQuestionSequence() {
        return questionSequence;
    }

    public void setQuestionSequence(int questionSequence) {
        this.questionSequence = questionSequence;
    }

    public String getQuestionTenant() {
        return questionTenant;
    }

    public void setQuestionTenant(String questionTenant) {
        this.questionTenant = questionTenant;
    }

    public String getQuestionClause() {
        return questionClause;
    }

    public void setQuestionClause(String questionClause) {
        this.questionClause = questionClause;
    }

    public String getQuestionTooltip() {
        return questionTooltip;
    }

    public void setQuestionTooltip(String questionTooltip) {
        this.questionTooltip = questionTooltip;
    }

    public double getQuestionWeight() {
        return questionWeight;
    }

    public void setQuestionWeight(double questionWeight) {
        this.questionWeight = questionWeight;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionSequence=" + questionSequence +
                ", questionTenant='" + questionTenant + '\'' +
                ", questionClause='" + questionClause + '\'' +
                ", questionTooltip='" + questionTooltip + '\'' +
                ", questionWeight=" + questionWeight +
                ", options=" + options +
                '}';
    }
}
