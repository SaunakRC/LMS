package LearningSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import LearningSystem.Entity.User;
import LearningSystem.Service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserService uservice;
    
    @PostMapping("/signup")
    public User signup(@RequestBody User user) {

        return uservice.signup(user);
    }
    
    @PostMapping("/signin")
    public User signin(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session) {

        User user = uservice.signin(email, password);

        if (user == null) {
            throw new RuntimeException("Invalid email or password");
        }

        
        session.setAttribute("loginUser", user);

        return user;
    }
    
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logout successful";
    }
    
    @GetMapping("/profile")
    public User profile(HttpSession session) {
        return (User) session.getAttribute("loginUser");
    }
}