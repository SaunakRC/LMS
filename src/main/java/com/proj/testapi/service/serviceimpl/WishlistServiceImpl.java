package com.proj.testapi.service.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.testapi.entity.Wishlist;
import com.proj.testapi.repository.WishlistRepository;
import com.proj.testapi.service.WishlistService;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wrepo;

    @Override
    public Wishlist addToWishlist(Long userId, Long courseId) {

        Wishlist existing =
                wrepo.findByUserIdAndCourseId(userId, courseId);

        if (existing != null) {
            return existing;
        }

        Wishlist wishlist = new Wishlist();

        wishlist.setUserId(userId);
        wishlist.setCourseId(courseId);
        wishlist.setAddedDate(LocalDateTime.now());

        return wrepo.save(wishlist);
    }

    @Override
    public List<Wishlist> getMyWishlist(Long userId) {

        return wrepo.findByUserId(userId);
    }

    @Override
    public String removeFromWishlist(Long userId, Long courseId) {

        Wishlist wishlist =
                wrepo.findByUserIdAndCourseId(userId, courseId);

        if (wishlist == null) {
            return "Course not found in wishlist";
        }

        wrepo.delete(wishlist);

        return "Course removed from wishlist";
    }

    @Override
    public boolean isInWishlist(Long userId, Long courseId) {

        return wrepo.findByUserIdAndCourseId(
                userId,
                courseId
        ) != null;
    }
}