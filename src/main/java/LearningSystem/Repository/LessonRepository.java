package LearningSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import LearningSystem.Entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    List<Lesson> findByCourseId(int courseId);

}