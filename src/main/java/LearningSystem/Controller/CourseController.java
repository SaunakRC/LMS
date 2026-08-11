package LearningSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import LearningSystem.Entity.Course;
import LearningSystem.Service.CourseService;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {

    @Autowired
    CourseService cservice;


    
    @PostMapping("/create")
    public Course createCourse(@RequestBody Course course) {

        return cservice.createCourse(course);
    }


    
    @GetMapping("/all")
    public List<Course> getAllCourses() {

        return cservice.getAllCourses();
    }


  
    @GetMapping("/{id}")
    public Course getCourse(@PathVariable int id) {

        return cservice.getCourseById(id);
    }


    
    @PutMapping("/update/{id}")
    public Course updateCourse(
            @PathVariable int id,
            @RequestBody Course course) {

        return cservice.updateCourse(id, course);
    }


    
    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable int id) {

        return cservice.deleteCourse(id);
    }


    
    @GetMapping("/teacher/{instructor}")
    public List<Course> getTeacherCourses(
            @PathVariable String instructor) {

        return cservice.getTeacherCourses(instructor);
    }

}