package LearningSystem.Service.Serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LearningSystem.Entity.Course;
import LearningSystem.Repository.CourseRepository;
import LearningSystem.Service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository crepo;


    @Override
    public Course createCourse(Course course) {

        return crepo.save(course);
    }


    @Override
    public List<Course> getAllCourses() {

        return crepo.findAll();
    }


    @Override
    public Course getCourseById(int id) {

        return crepo.findById(id).orElse(null);
    }


    @Override
    public Course updateCourse(int id, Course course) {

        Course oldCourse = crepo.findById(id).orElse(null);

        if (oldCourse == null) {
            return null;
        }

        oldCourse.setTitle(course.getTitle());
        oldCourse.setDescription(course.getDescription());
        oldCourse.setCategory(course.getCategory());
        oldCourse.setLevel(course.getLevel());
        oldCourse.setPrice(course.getPrice());
        oldCourse.setDuration(course.getDuration());
        oldCourse.setInstructor(course.getInstructor());
        oldCourse.setThumbnail(course.getThumbnail());
        oldCourse.setStatus(course.getStatus());

        return crepo.save(oldCourse);
    }


    @Override
    public String deleteCourse(int id) {

        if (!crepo.existsById(id)) {
            return "Course not found";
        }

        crepo.deleteById(id);

        return "Course deleted successfully";
    }


    @Override
    public List<Course> getTeacherCourses(String instructor) {

        return crepo.findByInstructor(instructor);
    }

}