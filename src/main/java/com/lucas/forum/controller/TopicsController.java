package com.lucas.forum.controller;

import com.lucas.forum.controller.DTO.DetailsTopicDTO;
import com.lucas.forum.controller.DTO.TopicDTO;
import com.lucas.forum.controller.form.TopicForm;
import com.lucas.forum.model.Topic;
import com.lucas.forum.repository.CourseRepository;
import com.lucas.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<TopicDTO> list(String courseName) {
        List<Topic> topics;
        if (courseName == null) {
            topics = topicRepository.findAll();
        } else {
            topics = topicRepository.findByCourseName(courseName);
        }
        return TopicDTO.convert(topics);
    }

    @PostMapping
    public ResponseEntity<TopicDTO> register(@RequestBody @Valid TopicForm topicForm, UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = topicForm.convert(courseRepository);
        topicRepository.save(topic);

        URI uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDTO(topic));
    }

    @GetMapping("/{id}")
    public DetailsTopicDTO details(@PathVariable Long id) {
        Topic topic = topicRepository.getById(id);
        return new DetailsTopicDTO(topic);
    }
}
