package com.proj.testapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.testapi.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    Wishlist findByUserIdAndCourseId(Long userId, Long courseId);

    List<Wishlist> findByUserId(Long userId);
}