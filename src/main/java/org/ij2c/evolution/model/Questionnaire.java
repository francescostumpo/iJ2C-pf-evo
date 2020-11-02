package org.ij2c.evolution.model;

import io.quarkus.mongodb.panache.MongoEntity;
import org.bson.types.ObjectId;
import org.ij2c.evolution.model.support.QuestionAnswer;

import java.util.List;

@MongoEntity(collection = "questionnaires")
public class Questionnaire {

    private ObjectId id;
    private List<QuestionAnswer> questionAnswerList;
    private double resultValue;
    private String resultClause;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }


    public List<QuestionAnswer> getQuestionAnswerList() {
        return questionAnswerList;
    }

    public void setQuestionAnswerList(List<QuestionAnswer> questionAnswerList) {
        this.questionAnswerList = questionAnswerList;
    }

    public double getResultValue() {
        return resultValue;
    }

    public void setResultValue(double resultValue) {
        this.resultValue = resultValue;
    }

    public String getResultClause() {
        return resultClause;
    }

    public void setResultClause(String resultClause) {
        this.resultClause = resultClause;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", questionAnswerList=" + questionAnswerList +
                ", resultValue=" + resultValue +
                ", resultClause='" + resultClause + '\'' +
                '}';
    }
}
