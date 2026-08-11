package LearningSystem.Service;

import java.util.List;

import LearningSystem.Entity.Wishlist;

public interface WishlistService {

    Wishlist addToWishlist(int userId, int courseId);

    List<Wishlist> getMyWishlist(int userId);

    String removeFromWishlist(int userId, int courseId);

    boolean isInWishlist(int userId, int courseId);
}