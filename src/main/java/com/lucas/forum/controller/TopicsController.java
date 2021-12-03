package com.lucas.forum.controller;

import com.lucas.forum.controller.DTO.TopicDTO;
import com.lucas.forum.model.Course;
import com.lucas.forum.model.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicsController {

    @RequestMapping("/topics")
    public List<TopicDTO> list(){
        Topic topic = new Topic("Duvida", "Duvida com Spring", new Course("Spring", "Programacao"));

        return TopicDTO.convert(Arrays.asList(topic, topic, topic));
    }
}
