package com.proj.testapi.service.serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import com.proj.testapi.service.LessonService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.proj.testapi.entity.Lesson;
import com.proj.testapi.entity.Module;
import com.proj.testapi.repository.LessonRepository;
import com.proj.testapi.repository.ModuleRepository;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final ModuleRepository moduleRepository;

    private final Path videoDirectory =
            Paths.get("uploads/videos");

    private final Path notesDirectory =
            Paths.get("uploads/notes");

    public LessonServiceImpl(
            LessonRepository lessonRepository,
            ModuleRepository moduleRepository) {

        this.lessonRepository = lessonRepository;
        this.moduleRepository = moduleRepository;

        try {
            Files.createDirectories(videoDirectory);
            Files.createDirectories(notesDirectory);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Could not create upload directories", e);
        }
    }

    // CREATE LESSON
    @Override
    public Lesson createLesson(
            Long moduleId,
            String title,
            String description,
            int lessonOrder,
            MultipartFile video,
            MultipartFile notes) {

        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Module not found with ID: " + moduleId));

        Lesson lesson = new Lesson();

        lesson.setTitle(title);
        lesson.setDescription(description);
        lesson.setLessonOrder(lessonOrder);
        lesson.setModule(module);

        // Upload video
        if (video != null && !video.isEmpty()) {

            String videoFileName =
                    saveFile(video, videoDirectory);

            lesson.setVideoUrl(
                    "/uploads/videos/" + videoFileName);
        }

        // Upload notes
        if (notes != null && !notes.isEmpty()) {

            String notesFileName =
                    saveFile(notes, notesDirectory);

            lesson.setNotesUrl(
                    "/uploads/notes/" + notesFileName);
        }

        return lessonRepository.save(lesson);
    }

    // GET LESSONS BY MODULE
    @Override
    public List<Lesson> getLessonsByModule(Long moduleId) {

        if (!moduleRepository.existsById(moduleId)) {
            throw new RuntimeException(
                    "Module not found with ID: " + moduleId);
        }

        return lessonRepository
                .findByModuleIdOrderByLessonOrderAsc(moduleId);
    }

    // GET ONE LESSON
    @Override
    public Lesson getLessonById(Long lessonId) {

        return lessonRepository.findById(lessonId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Lesson not found with ID: " + lessonId));
    }

    // UPDATE LESSON
    @Override
    public Lesson updateLesson(
            Long lessonId,
            String title,
            String description,
            int lessonOrder,
            MultipartFile video,
            MultipartFile notes) {

        Lesson lesson = getLessonById(lessonId);

        lesson.setTitle(title);
        lesson.setDescription(description);
        lesson.setLessonOrder(lessonOrder);

        // Replace old video
        if (video != null && !video.isEmpty()) {

            deleteFile(lesson.getVideoUrl());

            String videoFileName =
                    saveFile(video, videoDirectory);

            lesson.setVideoUrl(
                    "/uploads/videos/" + videoFileName);
        }

        // Replace old notes
        if (notes != null && !notes.isEmpty()) {

            deleteFile(lesson.getNotesUrl());

            String notesFileName =
                    saveFile(notes, notesDirectory);

            lesson.setNotesUrl(
                    "/uploads/notes/" + notesFileName);
        }

        return lessonRepository.save(lesson);
    }

    // DELETE LESSON
    @Override
    public void deleteLesson(Long lessonId) {

        Lesson lesson = getLessonById(lessonId);

        // Delete video
        deleteFile(lesson.getVideoUrl());

        // Delete notes
        deleteFile(lesson.getNotesUrl());

        lessonRepository.delete(lesson);
    }

    // SAVE FILE
    private String saveFile(
            MultipartFile file,
            Path directory) {

        try {

            String originalFilename =
                    file.getOriginalFilename();

            String extension = "";

            if (originalFilename != null &&
                    originalFilename.contains(".")) {

                extension =
                        originalFilename.substring(
                                originalFilename.lastIndexOf("."));
            }

            String filename =
                    UUID.randomUUID() + extension;

            Path target =
                    directory.resolve(filename);

            Files.copy(
                    file.getInputStream(),
                    target,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return filename;

        } catch (IOException e) {

            throw new RuntimeException(
                    "Could not save file", e);
        }
    }

    // DELETE FILE
    private void deleteFile(String url) {

        if (url == null || url.isEmpty()) {
            return;
        }

        try {

            String filePath =
                    url.replace("/uploads/", "");

            Path path =
                    Paths.get("uploads")
                            .resolve(filePath);

            Files.deleteIfExists(path);

        } catch (IOException e) {

            System.out.println(
                    "Could not delete file: "
                            + e.getMessage());
        }
    }
}