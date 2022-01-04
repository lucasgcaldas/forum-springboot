package com.lucas.forum.repository;

import com.lucas.forum.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Page<Topic> findByCourseName(String courseName, Pageable pageable);
}
