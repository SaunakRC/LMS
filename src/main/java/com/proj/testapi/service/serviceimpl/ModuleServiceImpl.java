package com.proj.testapi.service.serviceimpl;

import java.util.List;

import com.proj.testapi.service.ModuleService;
import org.springframework.stereotype.Service;

import com.proj.testapi.entity.Course;
import com.proj.testapi.entity.Module;
import com.proj.testapi.repository.CourseRepository;
import com.proj.testapi.repository.ModuleRepository;

@Service
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;

    public ModuleServiceImpl(
            ModuleRepository moduleRepository,
            CourseRepository courseRepository) {

        this.moduleRepository = moduleRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Module createModule(
            Long courseId,
            String title,
            String description,
            int moduleOrder) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found with ID: " + courseId));

        Module module = new Module();

        module.setTitle(title);
        module.setDescription(description);
        module.setModuleOrder(moduleOrder);
        module.setCourse(course);

        return moduleRepository.save(module);
    }

    @Override
    public List<Module> getModulesByCourse(Long courseId) {

        if (!courseRepository.existsById(courseId)) {
            throw new RuntimeException(
                    "Course not found with ID: " + courseId);
        }

        return moduleRepository
                .findByCourseIdOrderByModuleOrderAsc(courseId);
    }

    @Override
    public Module getModuleById(Long moduleId) {

        return moduleRepository.findById(moduleId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Module not found with ID: " + moduleId));
    }

    @Override
    public Module updateModule(
            Long moduleId,
            String title,
            String description,
            int moduleOrder) {

        Module module = getModuleById(moduleId);

        module.setTitle(title);
        module.setDescription(description);
        module.setModuleOrder(moduleOrder);

        return moduleRepository.save(module);
    }

    @Override
    public void deleteModule(Long moduleId) {

        if (!moduleRepository.existsById(moduleId)) {
            throw new RuntimeException(
                    "Module not found with ID: " + moduleId);
        }

        moduleRepository.deleteById(moduleId);
    }
}