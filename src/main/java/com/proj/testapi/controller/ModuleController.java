package com.proj.testapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proj.testapi.entity.Module;
import com.proj.testapi.service.ModuleService;

@RestController
@RequestMapping("/admin/modules")
@CrossOrigin(origins = "http://localhost:3000")
public class ModuleController {

    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    // CREATE MODULE
    @PostMapping("/course/{courseId}")
    public ResponseEntity<Module> createModule(
            @PathVariable Long courseId,

            @RequestParam String title,

            @RequestParam(required = false)
            String description,

            @RequestParam(defaultValue = "1")
            int moduleOrder) {

        Module module =
                moduleService.createModule(
                        courseId,
                        title,
                        description,
                        moduleOrder
                );

        return ResponseEntity.ok(module);
    }

    // GET ALL MODULES OF COURSE
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Module>> getModules(
            @PathVariable Long courseId) {

        return ResponseEntity.ok(
                moduleService.getModulesByCourse(courseId)
        );
    }

    // GET ONE MODULE
    @GetMapping("/{moduleId}")
    public ResponseEntity<Module> getModule(
            @PathVariable Long moduleId) {

        return ResponseEntity.ok(
                moduleService.getModuleById(moduleId)
        );
    }

    // UPDATE MODULE
    @PutMapping("/{moduleId}")
    public ResponseEntity<Module> updateModule(
            @PathVariable Long moduleId,

            @RequestParam String title,

            @RequestParam(required = false)
            String description,

            @RequestParam(defaultValue = "1")
            int moduleOrder) {

        return ResponseEntity.ok(
                moduleService.updateModule(
                        moduleId,
                        title,
                        description,
                        moduleOrder
                )
        );
    }

    // DELETE MODULE
    @DeleteMapping("/{moduleId}")
    public ResponseEntity<String> deleteModule(
            @PathVariable Long moduleId) {

        moduleService.deleteModule(moduleId);

        return ResponseEntity.ok(
                "Module deleted successfully"
        );
    }
}