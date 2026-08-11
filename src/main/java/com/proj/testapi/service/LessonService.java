package com.proj.testapi.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.proj.testapi.entity.Lesson;

public interface LessonService {

    Lesson createLesson(
            Long moduleId,
            String title,
            String description,
            int lessonOrder,
            MultipartFile video,
            MultipartFile notes
    );

    List<Lesson> getLessonsByModule(Long moduleId);

    Lesson getLessonById(Long lessonId);

    Lesson updateLesson(
            Long lessonId,
            String title,
            String description,
            int lessonOrder,
            MultipartFile video,
            MultipartFile notes
    );

    void deleteLesson(Long lessonId);
}