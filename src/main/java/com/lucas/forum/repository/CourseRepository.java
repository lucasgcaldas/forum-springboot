package com.lucas.forum.repository;

import com.lucas.forum.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByName(String courseName);
}
