package com.proj.testapi.service;

import java.util.List;

import com.proj.testapi.entity.Module;

public interface ModuleService {

    Module createModule(
            Long courseId,
            String title,
            String description,
            int moduleOrder
    );

    List<Module> getModulesByCourse(Long courseId);

    Module getModuleById(Long moduleId);

    Module updateModule(
            Long moduleId,
            String title,
            String description,
            int moduleOrder
    );

    void deleteModule(Long moduleId);
}