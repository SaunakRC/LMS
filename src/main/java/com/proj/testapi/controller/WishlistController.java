package com.proj.testapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.proj.testapi.entity.User;
import com.proj.testapi.entity.Wishlist;
import com.proj.testapi.service.WishlistService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("api/wishlist")
@CrossOrigin(
        origins = "http://localhost:3000",
        allowCredentials = "true"
)
public class WishlistController {

    @Autowired
    private WishlistService wservice;

    // ADD COURSE TO WISHLIST
    @PostMapping("/add/{courseId}")
    public Wishlist addWishlist(
            @PathVariable Long courseId,
            HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            throw new RuntimeException("Please login first");
        }

        Long userId = user.getId();

        return wservice.addToWishlist(userId, courseId);
    }

    // GET MY WISHLIST
    @GetMapping("/my-wishlist")
    public List<Wishlist> myWishlist(
            HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            throw new RuntimeException("Please login first");
        }

        Long userId = user.getId();

        return wservice.getMyWishlist(userId);
    }

    // REMOVE COURSE FROM WISHLIST
    @DeleteMapping("/remove/{courseId}")
    public String removeWishlist(
            @PathVariable Long courseId,
            HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            throw new RuntimeException("Please login first");
        }

        Long userId = user.getId();

        return wservice.removeFromWishlist(
                userId,
                courseId
        );
    }

    // CHECK COURSE IN WISHLIST
    @GetMapping("/check/{courseId}")
    public boolean checkWishlist(
            @PathVariable Long courseId,
            HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return false;
        }

        Long userId = user.getId();

        return wservice.isInWishlist(
                userId,
                courseId
        );
    }
}