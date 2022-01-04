package com.lucas.forum.controller.DTO;

import com.lucas.forum.model.Topic;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TopicDTO {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime dateCreation;

    public TopicDTO(Topic topic){
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.dateCreation = topic.getDateCreation();
    }

    public static Page<TopicDTO> convert(Page<Topic> topics) {
        return topics.map(TopicDTO::new);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }
}
