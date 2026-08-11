package LearningSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import LearningSystem.Entity.User;
import LearningSystem.Entity.Wishlist;
import LearningSystem.Service.WishlistService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin(origins = "http://localhost:3000")
public class WishlistController {

    @Autowired
    private WishlistService wservice;

    
    @PostMapping("/add/{courseId}")
    public Wishlist addWishlist(
            @PathVariable int courseId,
            HttpSession session) {

        User user = (User) session.getAttribute("loginUser");

        if (user == null) {
            throw new RuntimeException("Please login first");
        }

        int userId = user.getId();

        return wservice.addToWishlist(userId, courseId);
    }

    
    @GetMapping("/my-wishlist")
    public List<Wishlist> myWishlist(HttpSession session) {

        User user = (User) session.getAttribute("loginUser");

        if (user == null) {
            throw new RuntimeException("Please login first");
        }

        int userId = user.getId();

        return wservice.getMyWishlist(userId);
    }

    
    @DeleteMapping("/remove/{courseId}")
    public String removeWishlist(
            @PathVariable int courseId,
            HttpSession session) {

        User user = (User) session.getAttribute("loginUser");

        if (user == null) {
            throw new RuntimeException("Please login first");
        }

        int userId = user.getId();

        return wservice.removeFromWishlist(userId, courseId);
    }

    
    @GetMapping("/check/{courseId}")
    public boolean checkWishlist(
            @PathVariable int courseId,
            HttpSession session) {

        User user = (User) session.getAttribute("loginUser");

        if (user == null) {
            return false;
        }

        return wservice.isInWishlist(user.getId(), courseId);
    }
}