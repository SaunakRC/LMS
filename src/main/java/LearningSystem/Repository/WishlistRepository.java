package LearningSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import LearningSystem.Entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

    Wishlist findByUserIdAndCourseId(int userId, int courseId);

    List<Wishlist> findByUserId(int userId);
}