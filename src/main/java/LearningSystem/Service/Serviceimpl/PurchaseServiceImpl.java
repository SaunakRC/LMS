package LearningSystem.Service.Serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LearningSystem.Entity.Course;
import LearningSystem.Entity.Purchase;
import LearningSystem.Entity.User;
import LearningSystem.Repository.CourseRepository;
import LearningSystem.Repository.PurchaseRepository;
import LearningSystem.Repository.UserRepository;
import LearningSystem.Service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository prepo;

    @Autowired
    private UserRepository urepo;

    @Autowired
    private CourseRepository crepo;


   

    @Override
    public Purchase buyCourse(int userId, int courseId) {

        
        User user = urepo.findById(userId).orElse(null);

        if (user == null) {
            throw new RuntimeException("User not found");
        }


        
        Course course = crepo.findById(courseId).orElse(null);

        if (course == null) {
            throw new RuntimeException("Course not found");
        }


        
        if (prepo.existsByUserIdAndCourseId(userId, courseId)) {

            throw new RuntimeException(
                    "You have already purchased this course"
            );
        }


        
        Purchase purchase = new Purchase();

        purchase.setUserId(user.getId());

        purchase.setCourseId(course.getId());

        purchase.setUserEmail(user.getEmail());

        purchase.setAmount(course.getPrice());

        purchase.setStatus("SUCCESS");

        purchase.setPurchaseDate(LocalDateTime.now());


        // Save purchase
        return prepo.save(purchase);
    }


    

    @Override
    public List<Purchase> getMyCourses(int userId) {

        return prepo.findByUserId(userId);
    }


    @Override
    public boolean hasPurchased(int userId, int courseId) {

        return prepo.existsByUserIdAndCourseId(
                userId,
                courseId
        );
    }
}