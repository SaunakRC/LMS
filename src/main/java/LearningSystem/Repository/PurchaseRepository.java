package LearningSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import LearningSystem.Entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    List<Purchase> findByUserId(int userId);

    boolean existsByUserIdAndCourseId(int userId, int courseId);
}