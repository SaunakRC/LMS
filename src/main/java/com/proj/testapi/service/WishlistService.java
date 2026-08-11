package com.proj.testapi.service;

import java.util.List;

import com.proj.testapi.entity.Wishlist;

public interface WishlistService {

    Wishlist addToWishlist(Long userId, Long courseId);

    List<Wishlist> getMyWishlist(Long userId);

    String removeFromWishlist(Long userId, Long courseId);

    boolean isInWishlist(Long userId, Long courseId);
}