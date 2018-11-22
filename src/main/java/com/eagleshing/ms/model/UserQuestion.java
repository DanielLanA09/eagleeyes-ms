package com.eagleshing.ms.model;

import com.eagleshing.ms.model.audit.DateAudit;
import com.eagleshing.ms.model.type.QuestionType;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user_question")
public class UserQuestion extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Lob
    private String question;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private QuestionType type;

    @Column(name = "view_point")
    private int viewPoint;

    @Column(name = "is_hidden")
    private boolean isHidden;

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public int getViewPoint() {
        return viewPoint;
    }

    public void setViewPoint(int viewPoint) {
        this.viewPoint = viewPoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }
}
