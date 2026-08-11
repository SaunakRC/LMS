package LearningSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import LearningSystem.Entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findByInstructor(String instructor);

    List<Course> findByCategory(String category);

}