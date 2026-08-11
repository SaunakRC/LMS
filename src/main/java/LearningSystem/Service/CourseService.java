package LearningSystem.Service;

import java.util.List;

import LearningSystem.Entity.Course;

public interface CourseService {

    Course createCourse(Course course);

    List<Course> getAllCourses();

    Course getCourseById(int id);

    Course updateCourse(int id, Course course);

    String deleteCourse(int id);

    List<Course> getTeacherCourses(String instructor);

}