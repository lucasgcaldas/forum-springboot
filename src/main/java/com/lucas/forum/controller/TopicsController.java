package com.lucas.forum.controller;

import com.lucas.forum.controller.DTO.TopicDTO;
import com.lucas.forum.model.Topic;
import com.lucas.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @RequestMapping("/topics")
    public List<TopicDTO> list(){
        List<Topic> topics = topicRepository.findAll();
        return TopicDTO.convert(topics);
    }
}
