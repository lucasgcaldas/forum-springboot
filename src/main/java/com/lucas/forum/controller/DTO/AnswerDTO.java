package com.lucas.forum.controller.DTO;

import com.lucas.forum.model.Answer;

import java.time.LocalDateTime;

public class AnswerDTO {

    private Long id;
    private String message;
    private LocalDateTime dateCreation;
    private String authorName;

    public AnswerDTO(Answer answer) {
        this.id = answer.getId();
        this.message = answer.getMessage();
        this.dateCreation = answer.getDataCreation();
        this.authorName = answer.getAuthor().getName();
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public String getAuthorName() {
        return authorName;
    }
}
