package com.proj.testapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.testapi.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByModuleIdOrderByLessonOrderAsc(Long moduleId);
}