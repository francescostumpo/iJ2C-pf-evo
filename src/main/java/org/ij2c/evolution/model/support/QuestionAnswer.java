package org.ij2c.evolution.model.support;

public class QuestionAnswer {

    private String questionId;
    private String answerClause;
    private double answerValue;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswerClause() {
        return answerClause;
    }

    public void setAnswerClause(String answerClause) {
        this.answerClause = answerClause;
    }

    public double getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(double answerValue) {
        this.answerValue = answerValue;
    }
}
