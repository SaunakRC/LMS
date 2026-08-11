package LearningSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import LearningSystem.Entity.Purchase;
import LearningSystem.Entity.User;
import LearningSystem.Service.PurchaseService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/purchase")
@CrossOrigin(origins = "http://localhost:3000")
public class PurchaseController {

    @Autowired
    private PurchaseService pservice;


  

    @PostMapping("/buy/{courseId}")
    public Purchase buyCourse(
            @PathVariable int courseId,
            HttpSession session) {

        
        User user = (User) session.getAttribute("loginUser");

        
        if (user == null) {
            throw new RuntimeException("Please login first");
        }

        
        int userId = user.getId();

        
        return pservice.buyCourse(userId, courseId);
    }


  

    @GetMapping("/my-purchases")
    public List<Purchase> myPurchases(HttpSession session) {

        User user = (User) session.getAttribute("loginUser");

        if (user == null) {
            throw new RuntimeException("Please login first");
        }

        int userId = user.getId();

        return pservice.getMyCourses(userId);
    }



    @GetMapping("/check/{courseId}")
    public boolean checkPurchase(
            @PathVariable int courseId,
            HttpSession session) {

        User user = (User) session.getAttribute("loginUser");

        if (user == null) {
            throw new RuntimeException("Please login first");
        }

        int userId = user.getId();

        return pservice.hasPurchased(userId, courseId);
    }
}