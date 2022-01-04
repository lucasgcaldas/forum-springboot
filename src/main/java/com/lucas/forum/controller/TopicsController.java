package com.lucas.forum.controller;

import com.lucas.forum.controller.DTO.DetailsTopicDTO;
import com.lucas.forum.controller.DTO.TopicDTO;
import com.lucas.forum.controller.form.TopicForm;
import com.lucas.forum.controller.form.TopicFormUpdate;
import com.lucas.forum.model.Topic;
import com.lucas.forum.repository.CourseRepository;
import com.lucas.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public Page<TopicDTO> list(@RequestParam(required = false) String courseName, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

        Page<Topic> topics;
        if (courseName == null) {
            topics = topicRepository.findAll(pageable);
        } else {
            topics = topicRepository.findByCourseName(courseName, pageable);
        }
        return TopicDTO.convert(topics);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicDTO> register(@RequestBody @Valid TopicForm topicForm, UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = topicForm.convert(courseRepository);
        topicRepository.save(topic);

        URI uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDTO(topic));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsTopicDTO> details(@PathVariable Long id) {
        Optional<Topic> topic = topicRepository.findById(id);
        return topic.map(value -> ResponseEntity.ok(new DetailsTopicDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicDTO> update(@PathVariable Long id, @RequestBody @Valid TopicFormUpdate formUpdate) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if(optionalTopic.isPresent()){
            Topic topic = formUpdate.update(id, topicRepository);
            return ResponseEntity.ok(new TopicDTO(topic));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isPresent()){
            topicRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
