package com.proj.testapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.testapi.entity.Module;

public interface ModuleRepository extends JpaRepository<Module, Long> {

    List<Module> findByCourseIdOrderByModuleOrderAsc(Long courseId);
}