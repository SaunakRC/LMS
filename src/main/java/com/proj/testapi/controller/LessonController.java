package com.proj.testapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.proj.testapi.entity.Lesson;
import com.proj.testapi.service.LessonService;

@RestController
@RequestMapping("/admin/lessons")
@CrossOrigin(origins = "http://localhost:3000")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    // CREATE LESSON
    @PostMapping(
            value = "/module/{moduleId}",
            consumes = "multipart/form-data"
    )
    public ResponseEntity<Lesson> createLesson(

            @PathVariable Long moduleId,

            @RequestParam String title,

            @RequestParam(required = false)
            String description,

            @RequestParam(defaultValue = "1")
            int lessonOrder,

            @RequestParam(required = false)
            MultipartFile video,

            @RequestParam(required = false)
            MultipartFile notes) {

        Lesson lesson =
                lessonService.createLesson(
                        moduleId,
                        title,
                        description,
                        lessonOrder,
                        video,
                        notes
                );

        return ResponseEntity.ok(lesson);
    }

    // GET LESSONS OF MODULE
    @GetMapping("/module/{moduleId}")
    public ResponseEntity<List<Lesson>> getLessons(
            @PathVariable Long moduleId) {

        return ResponseEntity.ok(
                lessonService.getLessonsByModule(moduleId)
        );
    }

    // GET ONE LESSON
    @GetMapping("/{lessonId}")
    public ResponseEntity<Lesson> getLesson(
            @PathVariable Long lessonId) {

        return ResponseEntity.ok(
                lessonService.getLessonById(lessonId)
        );
    }

    // UPDATE LESSON
    @PutMapping(
            value = "/{lessonId}",
            consumes = "multipart/form-data"
    )
    public ResponseEntity<Lesson> updateLesson(

            @PathVariable Long lessonId,

            @RequestParam String title,

            @RequestParam(required = false)
            String description,

            @RequestParam(defaultValue = "1")
            int lessonOrder,

            @RequestParam(required = false)
            MultipartFile video,

            @RequestParam(required = false)
            MultipartFile notes) {

        return ResponseEntity.ok(
                lessonService.updateLesson(
                        lessonId,
                        title,
                        description,
                        lessonOrder,
                        video,
                        notes
                )
        );
    }

    // DELETE LESSON
    @DeleteMapping("/{lessonId}")
    public ResponseEntity<String> deleteLesson(
            @PathVariable Long lessonId) {

        lessonService.deleteLesson(lessonId);

        return ResponseEntity.ok(
                "Lesson deleted successfully"
        );
    }
}